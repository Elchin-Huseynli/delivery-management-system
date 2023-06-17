package com.company.deliverymanagement.service.impl;


import com.company.deliverymanagement.dto.request.CategoryRequestDto;
import com.company.deliverymanagement.dto.response.CategoryResponseDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.entity.model.Category;
import com.company.deliverymanagement.exception.CategoryNotFoundException;
import com.company.deliverymanagement.repository.CategoryRepository;
import com.company.deliverymanagement.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    //private final EntityMapper entityMapper;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDto> insert(CategoryRequestDto categoryRequestDto) {
        //Category category_ = entityMapper.CategoryRequestDtoToCategory(categoryRequestDto);
        Category category = modelMapper.map(categoryRequestDto,Category.class);
        categoryRepository.save(category);

        return ResponseEntity.ok(new ResponseDto("Register Successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> update(CategoryRequestDto categoryRequestDto) {
        //Category category_ = entityMapper.CategoryRequestDtoToCategory(categoryRequestDto);
        Category category = modelMapper.map(categoryRequestDto,Category.class);
        categoryRepository.save(category);
        return ResponseEntity.ok(new ResponseDto("Update Successfully"));
    }


    @Override
    public ResponseEntity<ResponseDto> delete(Long id) {
        categoryRepository.delete(
                categoryRepository.findById(id)
                        .orElseThrow(CategoryNotFoundException::new)
        );
        return ResponseEntity.ok(new ResponseDto("Delete Successfully"));
    }


    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(product -> modelMapper.map(product, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public CategoryResponseDto findById(Long id) {
//        return modelMapper.
//                CategoryResponseDtoToCategory(
//                        categoryRepository
//                        .findById(id)
//                        .orElseThrow(CategoryNotFoundException::new)
//                );
        return modelMapper.map(
                categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new)
                ,CategoryResponseDto.class);
    }

    public List<FoodResponseDto> findAllFood(Long id) {
//        return categoryRepository.findByFoods(id).stream().
//                map(entityMapper::FoodResponseDtoToFood).
//                collect(Collectors.toList());
        return categoryRepository.findByFoods(id).stream()
                .map(category -> modelMapper.map(category, FoodResponseDto.class))
                .collect(Collectors.toList());


    }

}
