
package com.example.Microservices.Repository;

import com.example.Microservices.Entity.VerifySignUpEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifySignUpRepository extends JpaRepository<VerifySignUpEntity, UUID> {
    VerifySignUpEntity findByToken(String hashedToken);
}
