
package com.example.Microservices.Repository;

import com.example.Microservices.Entity.AuthSignIn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthSignInRepository extends JpaRepository<AuthSignIn, UUID> {
    boolean existsByEmail(@NotNull @Email String email);

    AuthSignIn findByEmail(String email);
}
