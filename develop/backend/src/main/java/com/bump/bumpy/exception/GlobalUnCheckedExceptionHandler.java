package com.bump.bumpy.exception;

import com.bump.bumpy.util.dto.ResultMap;
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
    public ResponseEntity<ResultMap> handleGlobalException(Exception e) {
        log.error("Unchecked Exception", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultMap("message", e.getMessage()));
    }
}
