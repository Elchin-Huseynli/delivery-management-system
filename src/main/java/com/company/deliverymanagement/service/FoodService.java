package com.company.deliverymanagement.service;


import com.company.deliverymanagement.dto.request.FoodRequestDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FoodService {
    ResponseEntity<ResponseDto> insert(FoodRequestDto foodRequestDto);
    ResponseEntity<ResponseDto> update(FoodRequestDto foodRequestDto);
    ResponseEntity<ResponseDto> delete(Long id );
    List<FoodResponseDto> findAll();
    FoodResponseDto findById(Long id);
}
