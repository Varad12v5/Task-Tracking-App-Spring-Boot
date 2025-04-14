package com.vsb.tasks.controller;

import com.vsb.tasks.domin.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(RuntimeException e , WebRequest request) {
         ErrorResponse response=new ErrorResponse(
                 HttpStatus.BAD_REQUEST.value(),
                 e.getMessage(),
                 request.getDescription(false)
         );

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
