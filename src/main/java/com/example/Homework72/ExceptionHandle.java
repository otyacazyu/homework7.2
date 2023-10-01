package com.example.Homework72;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestControllerAdvice
public class ExceptionHandle {
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<Objects> handleConstraintViolationException(
            ConstraintViolationException ex){
        List<Objects> constraintErrors = new ArrayList<>(Objects);
        for (ConstraintViolation<?> violation : exgetConstraintViolations()){
            constraintErrors.add(violation.getRootBeanClass().getName()+""+
                    violation.getPropertyPath()+":"+ violation.getMessage());
        }
        return constraintErrors;
    }

}
