package com.vallejos.controller;

import com.vallejos.exception.ErrorResponse;
import com.vallejos.exception.ValidationExceptionMessage;
import com.vallejos.wrapper.ErrorResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseWrapper> handleValidationException(ValidationException e, WebRequest request) {
        String errorMessage = ValidationExceptionMessage.valueOf(e.getMessage()).getMessage();
        ErrorResponse errorResponse = new ErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                HttpStatus.BAD_REQUEST.value(),
                errorMessage // Usar el mensaje del enum como detalle del error
        );
        List<ErrorResponse> errors = Collections.singletonList(errorResponse);

        // Crear un objeto contenedor de errores
        ErrorResponseWrapper errorResponseWrapper = new ErrorResponseWrapper(errors);

        // Devolver la respuesta con el formato personalizado y el código de estado adecuado
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.BAD_REQUEST);
    }
}



