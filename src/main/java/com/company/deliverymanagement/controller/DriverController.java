package com.company.deliverymanagement.controller;


import com.company.deliverymanagement.dto.request.DriverRequestDto;
import com.company.deliverymanagement.dto.response.DriverResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
@Slf4j
public class DriverController {

    private final DriverService driverService;


    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> createDriver(@RequestBody DriverRequestDto driverRequestDto) {
        log.info("Creating driver: {}", driverRequestDto);
        ResponseEntity<ResponseDto> responseEntity = driverService.insert(driverRequestDto);
        log.info("Driver created with response: {}", responseEntity.getBody());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DriverResponseDto>> getAllDrivers() {
        log.info("Getting all driver");
        List<DriverResponseDto> driver = driverService.findAll();
        log.info("Retrieved all driver: {}", driver);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDto> getDriverById(@PathVariable Long id) {
        log.info("Getting driver by ID: {}", id);
        DriverResponseDto driverResponseDto = driverService.findById(id);
        log.info("Retrieved driver: {}", driverResponseDto);
        return ResponseEntity.ok(driverResponseDto);
    }

    @PutMapping("/{id}")  //Admin
    public ResponseEntity<ResponseDto> updateFood(@PathVariable Long id, @RequestBody DriverRequestDto foodRequestDto) {
        log.info("Updating driver with ID {}: {}", id, foodRequestDto);
        ResponseEntity<ResponseDto> responseEntity = driverService.updateFindById(foodRequestDto);
        log.info("Driver updated with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteFood(@PathVariable Long id) {
        log.info("Deleting driver with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = driverService.delete(id);
        log.info("Driver deleted with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }
}

