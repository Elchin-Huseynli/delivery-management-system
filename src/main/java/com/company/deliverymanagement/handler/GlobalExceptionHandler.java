package com.company.deliverymanagement.handler;

import com.company.deliverymanagement.dto.response.ExceptionResponse;
import com.company.deliverymanagement.enums.ExceptionEnum;
import com.company.deliverymanagement.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CategoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleCategoryException() {
        return ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(ExceptionEnum.CATEGORY_NOT_FOUND_EXCEPTION.getMessage())
                .build();
    }


    @ExceptionHandler({FoodNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleFoodException() {
        return ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(ExceptionEnum.FOOD_NOT_FOUND_EXCEPTION.getMessage())
                .build();
    }

    @ExceptionHandler({DriverNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleDriverException() {
        return ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(ExceptionEnum.DRIVER_NOT_FOUND_EXCEPTION.getMessage())
                .build();
    }

    @ExceptionHandler({OrderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleOrderException() {
        return ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(ExceptionEnum.ORDER_NOT_FOUND_EXCEPTION.getMessage())
                .build();
    }
    @ExceptionHandler({AppUserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleUserException() {
        return ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(ExceptionEnum.USER_NOT_FOUND_EXCEPTION.getMessage())
                .build();
    }
}
