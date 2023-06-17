package com.company.deliverymanagement.exception;


import com.company.deliverymanagement.enums.ExceptionEnum;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super(ExceptionEnum.CATEGORY_NOT_FOUND_EXCEPTION.getMessage());
    }
}
