package com.company.deliverymanagement.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionEnum {
    CATEGORY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Not found category"),
    FOOD_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Not found food"),
    DRIVER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Not found driver"),
    ORDER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Not found order"),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,"Not found User");

    private final HttpStatus httpStatus;
    private final String message;



}
