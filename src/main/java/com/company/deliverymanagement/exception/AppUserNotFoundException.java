package com.company.deliverymanagement.exception;

import com.company.deliverymanagement.enums.ExceptionEnum;

public class AppUserNotFoundException extends RuntimeException{
    public AppUserNotFoundException() {
        super(ExceptionEnum.USER_NOT_FOUND_EXCEPTION.getMessage());
    }
}
