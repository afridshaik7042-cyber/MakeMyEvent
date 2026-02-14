package com.example.Microservices.Configuration;

import com.example.Microservices.Service.JwtOncePerReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    public JwtOncePerReq jwtOncePerReq;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).sessionManagement((sm) -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests((auth) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[]{"/auth/**"})).permitAll().anyRequest()).authenticated()).addFilterBefore(this.jwtOncePerReq, UsernamePasswordAuthenticationFilter.class);
        return (SecurityFilterChain)http.build();
    }
}
