package com.company.deliverymanagement.entity.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "delivery_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String String;
    @ManyToMany
    List<Driver> drivers;
    Long cart_id;
    Integer status;
}
