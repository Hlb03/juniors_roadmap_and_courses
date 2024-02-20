package com.course_info.udemy_courses_info.exception_hadler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class RequestParamValidationExceptionHandler{

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseStatusException handleValidationExceptions(RuntimeException ex) {
        log.info("Validation exception happened: {}", ex.getMessage());
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage().split(":")[1].trim());
    }
}
