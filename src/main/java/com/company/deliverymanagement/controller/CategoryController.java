package com.company.deliverymanagement.controller;

import com.company.deliverymanagement.dto.request.CategoryRequestDto;
import com.company.deliverymanagement.dto.response.CategoryResponseDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> createCategory(@RequestBody CategoryRequestDto categoryDtoRequest) {
        log.info("Creating category: {}", categoryDtoRequest);
        ResponseEntity<ResponseDto> responseEntity = categoryService.insert(categoryDtoRequest);
        log.info("Category created with response: {}", responseEntity.getBody());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseEntity.getBody());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        log.info("Getting all categories");
        List<CategoryResponseDto> category = categoryService.findAll();
        log.info("Retrieved all categories: {}", category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        log.info("Getting category by ID: {}", id);
        CategoryResponseDto service = categoryService.findById(id);
        log.info("Retrieved category: {}", service);
        return ResponseEntity.ok(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto categoryDtoRequest) {
        log.info("Updating category with ID {}: {}", id, categoryDtoRequest);
        ResponseEntity<ResponseDto> responseEntity = categoryService.update(categoryDtoRequest);
        log.info("Category updated with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with ID: {}", id);
        ResponseEntity<ResponseDto> responseEntity = categoryService.delete(id);
        log.info("Category deleted with response: {}", responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @GetMapping("/{id}/foods")
    public ResponseEntity<List<FoodResponseDto>> getCategoryFood(@PathVariable Long id) {
        log.info("Getting all categories");
        List<FoodResponseDto> category = categoryService.findAllFood(id);
        log.info("Retrieved all categories: {}", category);
        return ResponseEntity.ok(category);
    }
}
