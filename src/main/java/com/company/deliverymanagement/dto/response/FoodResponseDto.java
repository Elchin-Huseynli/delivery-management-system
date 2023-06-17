package com.company.deliverymanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class FoodResponseDto {
    Long id;
    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotBlank
    Double amount;
    @NotBlank
    FoodDetailResponseDto foodDetails;
    @NotBlank
    Long categoryId;
    String image;

}