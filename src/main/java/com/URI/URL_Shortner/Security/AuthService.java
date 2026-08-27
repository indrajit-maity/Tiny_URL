package com.URI.URL_Shortner.Security;

import com.URI.URL_Shortner.Configuration.PasswordConfig;
import com.URI.URL_Shortner.Dto.LoginRequest;
import com.URI.URL_Shortner.Dto.LoginResponse;
import com.URI.URL_Shortner.Dto.SignupRequest;
import com.URI.URL_Shortner.Dto.SignupResponse;
import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private  final Authutill authutill;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public SignupResponse signup(SignupRequest signupRequest) {
        User user=userRepository.findByEmail(signupRequest.getEmail()).orElse(null);
        if(user!=null){
            throw new IllegalArgumentException("User already exits with this email");
        }
        try{
            user =userRepository.save(
                    User.builder()
                            .username(signupRequest.getUsername())
                            .email(signupRequest.getEmail())
                            .phoneNumber("..............")
                            .password(passwordEncoder.encode(signupRequest.getPassword()))
                            .providerId(null)
                            .authproviderType(AuthproviderType.EMAIL)
                            .roles(null)
                            .build()
            );
            log.info("User signup successful");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return modelMapper.map(user,SignupResponse.class);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        User user=(User) authentication.getPrincipal();
        String token=authutill.generateAccessToken(user);
        return new LoginResponse(token,user.getId());
    }
}
