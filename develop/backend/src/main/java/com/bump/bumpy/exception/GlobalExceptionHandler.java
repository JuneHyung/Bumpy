package com.bump.bumpy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@Slf4j
@Order(GlobalExceptionHandler.ODER)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public final static int ODER = 50;
    @ExceptionHandler(value = {
            UsernameNotFoundException.class,
            AuthenticationException.class,
            BadCredentialsException.class
    })
    public ResponseEntity<Object> handleAuthenticationException(Exception e, HttpStatus status) {
        log.error("Authorization Exception", e);
        return ResponseEntity.status(status).body("인증 실패");
    }

    @ExceptionHandler(value = {
            ValidationException.class,
            ConstraintViolationException.class      // Entity @NotNull에서 발생
    })
    public ResponseEntity<Object> handleValidationException(Exception e, HttpStatus status) {
        log.error("Validation Exception", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("누락되었거나, 잘못된 값입니다.");
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public  ResponseEntity<Object> handleRuntimeException(Exception e, HttpStatus status) {
        log.error("Runtime Exception", e);
        return ResponseEntity.status(status).body("알 수 없는 에러가 발생했습니다.");
    }
}
