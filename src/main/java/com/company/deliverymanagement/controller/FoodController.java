package com.company.deliverymanagement.controller;

import com.company.deliverymanagement.dto.request.FoodRequestDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
@Slf4j
public class FoodController {

    private final FoodService foodService;


    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> createFood(@RequestBody FoodRequestDto foodRequestDto) {
        log.info("Creating food: {}", foodRequestDto);
        ResponseEntity<ResponseDto> responseEntity = foodService.insert(foodRequestDto);
        log.info("Food created with response: {}", responseEntity.getBody());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FoodResponseDto>> getAllFoods() {
        log.info("Getting all foods");
        List<FoodResponseDto> food = foodService.findAll();
        log.info("Retrieved all foods: {}", food);
        return ResponseEntity.ok(food);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDto> getFoodById(@PathVariable Long id) {
        log.info("Getting food by ID: {}", id);
        FoodResponseDto foodResponseDto = foodService.findById(id);
        log.info("Retrieved food: {}", foodResponseDto);
        return ResponseEntity.ok(foodResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateFood(@PathVariable Long id, @RequestBody FoodRequestDto foodRequestDto) {
        log.info("Updating food with ID {}: {}", id, foodRequestDto);
        ResponseEntity<ResponseDto> responseEntity = foodService.update(foodRequestDto);
        log.info("Food updated with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteFood(@PathVariable Long id) {
        log.info("Deleting food with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = foodService.delete(id);
        log.info("Food deleted with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
