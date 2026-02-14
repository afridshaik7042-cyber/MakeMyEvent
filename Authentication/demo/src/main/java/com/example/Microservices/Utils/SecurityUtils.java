
package com.example.Microservices.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    public String tokenHash(String token) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(token.getBytes(StandardCharsets.UTF_8));
        String hashToken = HexFormat.of().formatHex(bytes);
        return hashToken;
    }
}
