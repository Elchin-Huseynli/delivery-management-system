package com.company.deliverymanagement.repository;

import com.company.deliverymanagement.entity.model.Category;
import com.company.deliverymanagement.entity.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {


    @Query("SELECT au.foods FROM Category au WHERE au.id = :id")
    List<Food> findByFoods(@Param("id") Long id);


}
