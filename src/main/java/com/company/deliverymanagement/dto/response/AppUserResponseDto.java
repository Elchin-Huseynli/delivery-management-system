package com.company.deliverymanagement.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AppUserResponseDto {
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
    Long roleId;
}


