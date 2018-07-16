package com.novway.buildit.exception.handler;

import com.novway.buildit.exception.BaseException;
import com.novway.buildit.exception.StatusCodes;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class DataValidationExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<BaseException> handleBadInput(ConstraintViolationException ex) {
        BaseException be = new BaseException();
        be.setMessage(StatusCodes.DATA_VALIDATION_ERROR);
        Set vioations = ex.getConstraintViolations();
        ConstraintViolation v = (ConstraintViolation) vioations.toArray()[0];
        be.setError(v.getPropertyPath() + " " + v.getMessage());
        return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<BaseException> DataFormatValidation(MethodArgumentTypeMismatchException ex, Locale locale){
        BaseException be = new BaseException();
        be.setMessage(messageSource.getMessage(StatusCodes.DATA_VALIDATION_ERROR,null,locale));
        be.setError(ex.getMessage());
        return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
    }

}
