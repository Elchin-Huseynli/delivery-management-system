package com.company.deliverymanagement.service;


import com.company.deliverymanagement.dto.request.OrderRequestDto;
import com.company.deliverymanagement.dto.response.OrderResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    List<OrderResponseDto> findAll();
    OrderResponseDto findById(Long id);
    ResponseEntity<ResponseDto> update(OrderRequestDto orderRequestDto);

}
