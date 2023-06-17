package com.company.deliverymanagement.entity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Double totalBudget;
    LocalDateTime createdAt;
    Integer totalCustomerCount;
    Integer totalDriverCount;
    Double dailyRevenue;
    Integer dailyOrderCount;
    Double monthlyRevenue;
    Integer monthlyOrderCount;
    Double yearlyRevenue;
    Integer yearlyOrderCount;
    Integer totalAmount;
}
