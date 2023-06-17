package com.company.deliverymanagement.service;


import com.company.deliverymanagement.dto.request.DriverRequestDto;
import com.company.deliverymanagement.dto.response.DriverResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {
    ResponseEntity<ResponseDto> insert(DriverRequestDto driverRequestDto);
    ResponseEntity<ResponseDto> updateFindById(DriverRequestDto driverRequestDto);
    ResponseEntity<ResponseDto> update(DriverRequestDto driverRequestDto);
    ResponseEntity<ResponseDto> delete(Long id );
    List<DriverResponseDto> findAll();
    DriverResponseDto findById(Long id);
}
