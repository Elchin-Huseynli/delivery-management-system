package com.company.deliverymanagement.entity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String token;
    LocalDateTime createdAt;
    LocalDateTime confirmedAt;
    Boolean confirm;
    @OneToOne
    AppUser user;


    public ConfirmationToken(AppUser appUser) {
        this.user = appUser;
    }
}
