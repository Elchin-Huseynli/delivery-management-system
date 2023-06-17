package com.company.deliverymanagement.service.impl;


import com.company.deliverymanagement.dto.request.FoodRequestDto;
import com.company.deliverymanagement.dto.response.FoodResponseDto;
import com.company.deliverymanagement.dto.response.ResponseDto;
import com.company.deliverymanagement.entity.model.Food;
import com.company.deliverymanagement.exception.FoodNotFoundException;
import com.company.deliverymanagement.repository.FoodRepository;
import com.company.deliverymanagement.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;


    //private final EntityMapper entityMapper;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDto> insert(FoodRequestDto foodRequestDto) {
        //Food food_ = entityMapper.FoodRequestDtoToFood(foodRequestDto);
        Food food = modelMapper.map(foodRequestDto,Food.class);
        foodRepository.save(food);

        return ResponseEntity.ok(new ResponseDto("Register Successfully"));
    }

    @Override
    public ResponseEntity<ResponseDto> update(FoodRequestDto foodRequestDto) {
        //Food food_ = entityMapper.FoodRequestDtoToFood(foodRequestDto);
        Food food = modelMapper.map(foodRequestDto,Food.class);
        foodRepository.save(food);
        return ResponseEntity.ok(new ResponseDto("Update Successfully"));
    }


    @Override
    public ResponseEntity<ResponseDto> delete(Long id) {
        foodRepository.delete(
                foodRepository.findById(id)
                        .orElseThrow(FoodNotFoundException::new)
        );
        return ResponseEntity.ok(new ResponseDto("Delete Successfully"));
    }


    @Override
    public List<FoodResponseDto> findAll() {
        return foodRepository.findAll().stream()
                .map(product -> modelMapper.map(product, FoodResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public FoodResponseDto findById(Long id) {
//        return modelMapper.
//                FoodResponseDtoToFood(
//                        foodRepository
//                        .findById(id)
//                        .orElseThrow(FoodNotFoundException::new)
//                );
        return modelMapper.map(
                foodRepository.findById(id).orElseThrow(FoodNotFoundException::new)
                ,FoodResponseDto.class);
    }


}
