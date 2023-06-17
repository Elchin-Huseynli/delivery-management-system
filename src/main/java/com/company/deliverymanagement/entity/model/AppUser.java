package com.company.deliverymanagement.entity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    LocalDate birthday;
    String email;
    String username;
    String password;
    String phoneNumber;
    @Builder.Default
    Boolean isEnabled = false;
    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;
}
