package com.company.deliverymanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AppUserRequestDto {

    @NotNull
    Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 1, max = 50, message = "Surname must be between 1 and 50 characters")
    String surname;


    @NotBlank(message = "Birthday cannot be blank")
    @Past
    LocalDate birthday;

    @NotBlank(message = "Email cannot be blank")
    @Email
    String email;

    @NotBlank
    @Size(min = 1, max = 50)
    String username;
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
    String password;

    @NotBlank(message = "Phone number cannot be blank")
    String phoneNumber;

    @Builder.Default
    Boolean isEnabled = false;

    @NotNull
    Long roleId;
}

