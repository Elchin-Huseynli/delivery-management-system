package com.company.deliverymanagement.exception;


import com.company.deliverymanagement.enums.ExceptionEnum;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super(ExceptionEnum.ORDER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
