package com.example.spring.mobile_app_ws.exceptions;

import com.example.spring.mobile_app_ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice // listen to all exceptions
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class) // e.g. NullPointerException, now it included all 'Exception'
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String localizedErrorMessage = ex.getLocalizedMessage();

        if(localizedErrorMessage == null) localizedErrorMessage = ex.toString();
        // localized error msg can be null, so handling that (meaning there can be an exception but error message can be null)

        ErrorMessage errorMessage = new ErrorMessage(new Date(), localizedErrorMessage);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserServiceException.class, NullPointerException.class})
    public ResponseEntity<Object> handleUserServiceException(Exception ex, WebRequest request) {

        String exceptionMessage = ex.getLocalizedMessage();

        if (exceptionMessage.isEmpty()) exceptionMessage = ex.toString();
        // localized error msg can be null, so handling that (meaning there can be an exception but error message can be null)

        ErrorMessage errorMessage = new ErrorMessage(new Date(), exceptionMessage);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}