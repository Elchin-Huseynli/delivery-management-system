package com.company.deliverymanagement.entity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FoodDetails {
    Integer size;
    Double prize;
    Double calories;


}
