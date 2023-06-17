package com.company.deliverymanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDto {

    @NotNull(message = "ID cannot be null")
    Long id;

    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Description is required")
    String description;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    Double amount;

    @Valid
    FoodDetailRequestDto foodDetails;

    @NotNull(message = "Category ID cannot be null")
    Long categoryId;
}
