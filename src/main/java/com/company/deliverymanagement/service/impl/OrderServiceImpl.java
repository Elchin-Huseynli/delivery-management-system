package com.company.deliverymanagement.service.impl;

import com.company.deliverymanagement.dto.request.OrderRequestDto;
import com.company.deliverymanagement.dto.response.OrderResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.entity.model.Order;
import com.company.deliverymanagement.exception.OrderNotFoundException;
import com.company.deliverymanagement.repository.OrderRepository;
import com.company.deliverymanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;
    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto findById(Long id) {
        return modelMapper.map(
                orderRepository.findById(id).orElseThrow(OrderNotFoundException::new)
                , OrderResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> update(OrderRequestDto orderRequestDto) {
        Order order = modelMapper.map(orderRequestDto,Order.class);
        orderRepository.save(order);
        return ResponseEntity.ok(new ResponseDto("Update Successfully"));
    }
}
