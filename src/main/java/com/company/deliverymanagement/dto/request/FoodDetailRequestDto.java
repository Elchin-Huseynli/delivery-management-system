package com.company.deliverymanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDetailRequestDto {
    @NotNull(message = "Size field is required")
    @Positive(message = "Size must be a positive number")
    Integer size;

    @NotNull(message = "Prize field is required")
    @Positive(message = "Prize must be a positive number")
    Double prize;

    @NotNull(message = "Calories field is required")
    @Positive(message = "Calories must be a positive number")
    Double calories;
    }
