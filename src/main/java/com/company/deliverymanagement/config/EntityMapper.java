package com.company.deliverymanagement.config;


import com.company.deliverymanagement.dto.request.CategoryRequestDto;
import com.company.deliverymanagement.dto.request.FoodRequestDto;
import com.company.deliverymanagement.dto.response.CategoryResponseDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.entity.model.*;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface EntityMapper {

    Category CategoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto CategoryResponseDtoToCategory(Category Category);
    CategoryRequestDto CategoryRequestDtoToCategory(Category Category);
    FoodResponseDto FoodResponseDtoToFood(Food food);
    Food FoodToResponseDto(FoodRequestDto foodRequestDto);

}
