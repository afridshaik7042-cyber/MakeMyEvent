package com.example.Microservices.Controllers;

import com.example.Microservices.Service.AuthService;
import java.security.NoSuchAlgorithmException;
import org.openapitools.api.AuthApi;
import org.openapitools.model.AuthResponse;
import org.openapitools.model.LogInRequest;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthApi {
    @Autowired
    public AuthService authService;

    public ResponseEntity<AuthResponse> userLogIn(LogInRequest logInRequest) {
        AuthResponse authResponse = this.authService.userLogin(logInRequest);
        return ResponseEntity.ok(authResponse);
    }

    public ResponseEntity<SignInResponse> userSignUp(SignInRequest signInRequest) throws NoSuchAlgorithmException {
        SignInResponse response = this.authService.userSignIn(signInRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"/hello"})
    public String hello() {
        return "Hello";
    }

    @GetMapping({"auth/verify"})
    public ResponseEntity<SignInResponse> verifyToken(@RequestParam String token) throws Exception {
        SignInResponse response = this.authService.verifyMyToken(token);
        return ResponseEntity.ok(response);
    }
}
