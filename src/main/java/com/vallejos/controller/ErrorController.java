package com.vallejos.controller;
import com.vallejos.exception.ErrorResponse;
import com.vallejos.exception.ValidationExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import javax.validation.ValidationException;

@ControllerAdvice
public class ErrorController {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e, WebRequest request) {
        String errorMessage = ValidationExceptionMessage.valueOf(e.getMessage()).getMessage();
        String fullErrorMessage = errorMessage ;

        ErrorResponse errorResponse = new ErrorResponse(fullErrorMessage);

        // Devolver la respuesta con el formato personalizado y el código de estado adecuado
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}







