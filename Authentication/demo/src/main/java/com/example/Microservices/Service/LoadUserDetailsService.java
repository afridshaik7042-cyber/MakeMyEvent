package com.example.Microservices.Service;

import com.example.Microservices.Entity.AuthSignIn;
import com.example.Microservices.Repository.AuthSignInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoadUserDetailsService implements UserDetailsService {
    @Autowired
    public AuthSignInRepository authSignInRepository;

    public UserDetails loadUserByUsername(String email) {
        AuthSignIn authSignIn = this.authSignInRepository.findByEmail(email);
        return User.builder().username(authSignIn.getEmail()).password(authSignIn.getPassword()).authorities(new String[]{"USER"}).build();
    }
}
