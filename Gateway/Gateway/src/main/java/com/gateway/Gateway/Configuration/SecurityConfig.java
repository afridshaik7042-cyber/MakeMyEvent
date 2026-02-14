package com.gateway.Gateway.Configuration;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf((csrf) -> csrf.disable()).authorizeExchange((exchange) -> ((ServerHttpSecurity.AuthorizeExchangeSpec.Access)exchange.pathMatchers(new String[]{"/auth/**"})).permitAll().anyExchange().authenticated()).oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())).build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        String secret = "9f3c2a1b4e8d7f9c6a2b3c4d5e6f7a8b9c0d1e2f3a4b5c6d7e8f9a0b1c2";
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(key).build();
    }
}
