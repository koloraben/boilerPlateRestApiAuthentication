package com.novway.buildit.exception.handler;

import com.novway.buildit.exception.BaseException;
import com.novway.buildit.exception.StatusCodes;
import com.novway.buildit.exception.customException.ElementNotFoundException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class TransactionExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(value = {TransactionSystemException.class})
    public ResponseEntity<BaseException> handleTxException(TransactionSystemException ex,Locale locale) {
        BaseException be = new BaseException();
        be.setMessage(messageSource.getMessage(StatusCodes.TRANSACTION_SYSTEM_ERROR,null, locale));
        Throwable t = ex.getCause();
        if(t instanceof ConstraintViolationException){
            ConstraintViolationException cve = (ConstraintViolationException)t;
            Set violations = cve.getConstraintViolations();
            ConstraintViolation v = (ConstraintViolation) violations.toArray()[0];
            be.setError(v.getPropertyPath() + " " + v.getMessage());
        }else {
            be.setError(ex.getMessage());
        }
        return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ElementNotFoundException.class})
    public ResponseEntity<BaseException> ChantierNotFound(ElementNotFoundException ex, Locale locale){
        BaseException be = new BaseException();
        be.setError(ex.getMessage());
        return new ResponseEntity(be, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<BaseException> accessDeniedException(AccessDeniedException ex, Locale locale){
        BaseException be = new BaseException();
        be.setError(ex.getMessage());
        return new ResponseEntity(be, HttpStatus.FORBIDDEN);
    }
}
