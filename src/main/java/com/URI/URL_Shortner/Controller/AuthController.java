package com.URI.URL_Shortner.Controller;


import com.URI.URL_Shortner.Dto.LoginRequest;
import com.URI.URL_Shortner.Dto.LoginResponse;
import com.URI.URL_Shortner.Dto.SignupRequest;
import com.URI.URL_Shortner.Dto.SignupResponse;
import com.URI.URL_Shortner.Security.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
