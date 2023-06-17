package com.company.deliverymanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponseDto {
    Long id;
    String String;
    Long driverId;
    Long cart_id;
    Integer status;
}
