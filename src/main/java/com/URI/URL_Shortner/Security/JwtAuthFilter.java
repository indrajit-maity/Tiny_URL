package com.URI.URL_Shortner.Security;


import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final Authutill authutill;
    private final UserRepository userRepository;
    private  final HandlerExceptionResolver handlerExceptionResolver;
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("incoming request:{}",request.getRequestURI());
        try{
            final String jwtToken=request.getHeader("Authorization");
            if(jwtToken==null || !jwtToken.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }
            String token=jwtToken.split("Bearer ")[1];
            String username=authutill.getUsernameFromToken(token);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                User user=userRepository.findByUsername(username).orElseThrow();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.info(SecurityContextHolder.getContext().getAuthentication().toString());
            }
            filterChain.doFilter(request,response);
        }
        catch (Exception ex){
            handlerExceptionResolver.resolveException(request,response,null,ex);
        }
    }
}
