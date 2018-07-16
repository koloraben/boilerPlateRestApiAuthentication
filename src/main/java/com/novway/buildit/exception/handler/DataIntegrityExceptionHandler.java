package com.novway.buildit.exception.handler;

import com.novway.buildit.exception.BaseException;
import com.novway.buildit.exception.StatusCodes;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DataIntegrityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<BaseException> handleException(DataIntegrityViolationException ex,Locale locale) {
        BaseException be = new BaseException();
        be.setMessage(messageSource.getMessage(StatusCodes.DATA_INTEGRITY_ERROR,null, locale));
        be.setError("Data constraint violated. " + ex.getMessage());
        return new ResponseEntity(be, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseException> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
            .stream()
            .map(objectError -> messageSource.getMessage(objectError, locale))
            .collect(Collectors.toList());
        BaseException be = new BaseException();
        return new ResponseEntity<>(be, HttpStatus.BAD_REQUEST);
    }
}
