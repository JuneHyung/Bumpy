package com.bump.bumpy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(GlobalExceptionHandler.ODER + 1)
@RestControllerAdvice
public class GlobalUnCheckedExceptionHandler {
    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<String> handleGlobalException(Exception e, HttpStatus status) {
        log.error("Unchecked Exception", e);
        return ResponseEntity.status(status).body("예기치 못한 에러 발생");
    }
}
