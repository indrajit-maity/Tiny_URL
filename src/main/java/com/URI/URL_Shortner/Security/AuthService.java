package com.URI.URL_Shortner.Security;

import com.URI.URL_Shortner.Configuration.PasswordConfig;
import com.URI.URL_Shortner.Dto.LoginRequest;
import com.URI.URL_Shortner.Dto.LoginResponse;
import com.URI.URL_Shortner.Dto.SignupRequest;
import com.URI.URL_Shortner.Dto.SignupResponse;
import com.URI.URL_Shortner.Entity.Type.AuthproviderType;
import com.URI.URL_Shortner.Entity.Type.RoleType;
import com.URI.URL_Shortner.Entity.User;
import com.URI.URL_Shortner.Exception.UnauthorizedException;
import com.URI.URL_Shortner.Exception.UserNotFoundException;
import com.URI.URL_Shortner.Exception.AccountLockedException;
import com.URI.URL_Shortner.Repository.UserRepository;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Authutill authutill;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    private final int MAX_FAILED_ATTEMPTS = 5;
    private final int LOCK_TIME_DURATION = 15 * 60 * 1000;

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
                            .phoneNumber(signupRequest.getPhoneNumber())
                            .password(passwordEncoder.encode(signupRequest.getPassword()))
                            .providerId(null)
                            .authproviderType(AuthproviderType.EMAIL)
                            .roles(Set.of(signupRequest.getRoles().toArray(new RoleType[0])))
                            .createdDate(LocalDateTime.now())
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
        try{
            User checkuser=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new UserNotFoundException("User not found with email: "+loginRequest.getEmail()));

            if(!checkuser.isAccountNonLocked()) {
                log.warn("login attempt on locked account.Username: {}, Email: {}", loginRequest.getUsername(), loginRequest.getEmail());
                long lockTimeRemaining=checkuser.getLockedAt()!=null? java.time.Duration.between(checkuser.getLockedAt(),LocalDateTime.now()).toMinutes():15;
                throw new AccountLockedException("Account is Locked.Please try again after "+lockTimeRemaining+" minutes");
            }
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
            handleSuccessfullLogin(loginRequest.getEmail());
            User user=(User) authentication.getPrincipal();
            String token=authutill.generateAccessToken(user);
            LoginResponse loginResponse=LoginResponse.builder()
                    .JWT_TOKEN(token)
                    .userId(user.getId())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .roles(Set.copyOf(user.getRoles()))
                    .build();
            log.info("Login successful for email: {}", loginRequest.getEmail());
            return loginResponse;
        }
        catch (BadCredentialsException ex){
            log.warn("Invalid credentials for email: {}", loginRequest.getEmail());
            int remainingAttempts=getRemainingAttempts(loginRequest.getEmail());
            handleFailedLogin(loginRequest.getEmail());
            String message="Invalid email or password."+remainingAttempts+" attempts remaining";
            throw new UnauthorizedException(message);
        }
    }

    @Transactional
    public void handleSuccessfullLogin(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: "+email));
        user.setAccountNonLocked(true);
        user.setFailedAttempts(0);
        userRepository.save(user);
    }

    public int getRemainingAttempts(String email){
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: "+email));
        return Math.max(0,MAX_FAILED_ATTEMPTS-user.getFailedAttempts());
    }

    @Transactional
    public void handleFailedLogin(String email){
        log.warn("Failed login Attempt for email:{}",email);
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not founr with email: "+email));
        int currentAttempts=user.getFailedAttempts()+1;
        user.setFailedAttempts(currentAttempts);
        if(currentAttempts>=MAX_FAILED_ATTEMPTS){
            user.setAccountNonLocked(false);
            log.info("Account locked for email:{} due to {} failed Attempts.",email,currentAttempts);
        }
        userRepository.save(user);
    }
}
