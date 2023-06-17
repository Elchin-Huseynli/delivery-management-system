package com.company.deliverymanagement.exception;


import com.company.deliverymanagement.enums.ExceptionEnum;

public class DriverNotFoundException extends RuntimeException{

    public DriverNotFoundException()  {
        super(ExceptionEnum.DRIVER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
