package com.company.deliverymanagement.repository;

import com.company.deliverymanagement.entity.model.ForgetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgetPasswordTokenRepository extends JpaRepository<ForgetPasswordToken,Long> {

    Optional<ForgetPasswordToken> findByToken(String token);
}
