package com.company.deliverymanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponseDto {
    Long id;
    String username;
    String password;
    Boolean isBusy;
}