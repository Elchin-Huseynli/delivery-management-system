package com.company.deliverymanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "Id cannot be null")
    Long id;

    @NotBlank(message = "String field cannot be blank")
    String string;

    @NotNull(message = "DriverId cannot be null")
    Long driverId;

    @NotNull(message = "CartId cannot be null")
    Long cartId;

    @NotNull(message = "Status cannot be null")
    String status;
}

