package com.company.deliverymanagement.repository;


import com.company.deliverymanagement.entity.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{
}
