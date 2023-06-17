package com.company.deliverymanagement.repository;

import com.company.deliverymanagement.entity.model.AppUser;
import com.company.deliverymanagement.entity.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String confirmationToken);



    @Query("SELECT au FROM ForgetPasswordToken au WHERE au.token = :token")
    Optional<AppUser> findByResetToken(String token);

}
