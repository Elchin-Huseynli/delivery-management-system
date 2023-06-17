package com.company.deliverymanagement.exception;


import com.company.deliverymanagement.enums.ExceptionEnum;

public class FoodNotFoundException extends RuntimeException{

    public FoodNotFoundException() {
        super(ExceptionEnum.FOOD_NOT_FOUND_EXCEPTION.getMessage());
    }
}
