package com.company.deliverymanagement.service.impl;


import com.company.deliverymanagement.dto.request.DriverRequestDto;
import com.company.deliverymanagement.dto.response.DriverResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.entity.model.Driver;
import com.company.deliverymanagement.exception.DriverNotFoundException;
import com.company.deliverymanagement.repository.DriverRepository;
import com.company.deliverymanagement.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverResponse;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDto> insert(DriverRequestDto driverRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> updateFindById(DriverRequestDto driverRequestDto) {
        Driver driver = modelMapper.map(driverRequestDto,Driver.class);
        driverResponse.save(driver);
        return ResponseEntity.ok(new ResponseDto("Update Successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> update(DriverRequestDto driverRequestDto) {
        Driver driver = modelMapper.map(driverRequestDto,Driver.class);
        driverResponse.save(driver);
        return ResponseEntity.ok(new ResponseDto("Update Successfully"));
    }

    public ResponseEntity<ResponseDto> delete(Long id) {
        driverResponse.delete(
                driverResponse.findById(id)
                        .orElseThrow(DriverNotFoundException::new)
        );
        return ResponseEntity.ok(new ResponseDto("Delete Successfully"));
    }

    @Override
    public List<DriverResponseDto> findAll() {
        return driverResponse.findAll().stream()
                .map(driver -> modelMapper.map(driver, DriverResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DriverResponseDto findById(Long id) {
        return modelMapper.map(
                driverResponse.findById(id).orElseThrow(DriverNotFoundException::new)
                ,DriverResponseDto.class);
    }
}
