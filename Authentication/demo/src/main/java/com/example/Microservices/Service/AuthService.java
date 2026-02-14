package com.example.Microservices.Service;

import com.example.Microservices.Entity.AuthSignIn;
import com.example.Microservices.Entity.VerifySignUpEntity;
import com.example.Microservices.Model.VerifySignUp;
import com.example.Microservices.Repository.AuthSignInRepository;
import com.example.Microservices.Repository.VerifySignUpRepository;
import com.example.Microservices.Utils.SecurityUtils;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;
import org.openapitools.model.AuthResponse;
import org.openapitools.model.LogInRequest;
import org.openapitools.model.SignInRequest;
import org.openapitools.model.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    public MailVerifyKafka mailVerifyKafka;
    @Autowired
    public AuthSignInRepository authSignInRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private VerifySignUpRepository verifySignUpRepository;
    @Autowired
    public JwtFilter jwtFilter;
    @Autowired
    public SecurityUtils securityUtils;

    public AuthResponse userLogin(LogInRequest logInRequest) {
        AuthResponse response = new AuthResponse();
        response.setExpireTime(3000);
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getEmail(), logInRequest.getPassword()));
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String role = this.authSignInRepository.findByEmail(logInRequest.getEmail()).role;
        String token = this.jwtFilter.jwtToken(logInRequest.getEmail(), role);
        response.setToken(token);
        response.setExpireTime(20000);
        return response;
    }

    public SignInResponse userSignIn(SignInRequest signInRequest) throws NoSuchAlgorithmException {
        if (this.authSignInRepository.existsByEmail(signInRequest.getEmail())) {
            SignInResponse response = new SignInResponse();
            response.setMessage("User Already Exists");
            response.setStatusCode("200");
            return response;
        } else {
            AuthSignIn authSignIn = new AuthSignIn();
            authSignIn.setId(UUID.randomUUID());
            authSignIn.setEmail(signInRequest.getEmail());
            authSignIn.setPassword(this.passwordEncoder.encode(signInRequest.getPassword()));
            authSignIn.setIsValidated(false);
            this.authSignInRepository.save(authSignIn);
            VerifySignUpEntity verifySignUpEntity = new VerifySignUpEntity();
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[32];
            random.nextBytes(bytes);
            String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
            String hashToken = this.securityUtils.tokenHash(token);
            System.out.println("Api call api " + token);
            verifySignUpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(30L));
            verifySignUpEntity.setToken(hashToken);
            verifySignUpEntity.setId(UUID.randomUUID());
            verifySignUpEntity.setUserId(signInRequest.getEmail());
            this.verifySignUpRepository.save(verifySignUpEntity);
            this.mailVerifyKafka.verifyMail(new VerifySignUp(signInRequest.getEmail(), token));
            SignInResponse response = new SignInResponse();
            response.setMessage("Verify mail sent to registered mailId");
            response.setStatusCode("200");
            return response;
        }
    }

    public SignInResponse verifyMyToken(String token) throws Exception {
        String hashedToken = this.securityUtils.tokenHash(token);
        System.out.println("token +" + hashedToken);
        System.out.println("Api token" + token);
        VerifySignUpEntity verifySignUpEntity = this.verifySignUpRepository.findByToken(hashedToken);
        if (verifySignUpEntity != null && verifySignUpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new Exception("Token Expired");
        } else {
            AuthSignIn authSignIn = this.authSignInRepository.findByEmail(verifySignUpEntity.getUserId());
            authSignIn.setIsValidated(true);
            this.authSignInRepository.save(authSignIn);
            this.verifySignUpRepository.delete(verifySignUpEntity);
            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setStatusCode("200");
            signInResponse.setMessage("Email verified successfully");
            return signInResponse;
        }
    }
}
