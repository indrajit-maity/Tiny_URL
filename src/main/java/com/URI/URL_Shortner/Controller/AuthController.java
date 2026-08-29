package com.URI.URL_Shortner.Controller;


import com.URI.URL_Shortner.Dto.*;
import com.URI.URL_Shortner.Entity.PasswordResetToken;
import com.URI.URL_Shortner.Security.AuthService;
//import com.URI.URL_Shortner.Security.OtpService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "Endpoints for user authentication and authorization")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupRequest));
    }


//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest){
//        log.info("Forgot password request received for email: {}", forgotPasswordRequest.getEmail());
//        try{
//            PasswordResetResponse response= OtpService.createPasswordResetRequest(forgotPasswordRequest);
//        }
//        catch (Exception ex){
//
//        }
//
//    }
}
