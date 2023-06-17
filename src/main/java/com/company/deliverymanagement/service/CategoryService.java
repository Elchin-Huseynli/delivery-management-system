package com.company.deliverymanagement.service;


import com.company.deliverymanagement.dto.request.CategoryRequestDto;
import com.company.deliverymanagement.dto.response.CategoryResponseDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ResponseDto> insert(CategoryRequestDto categoryRequestDto);
    ResponseEntity<ResponseDto> update(CategoryRequestDto categoryRequestDto);
    ResponseEntity<ResponseDto> delete(Long id );
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(Long id);
    List<FoodResponseDto> findAllFood(Long id);
}
