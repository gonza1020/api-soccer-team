package com.dux.api_soccer_team.exception;

import com.dux.api_soccer_team.dto.ErrorResponse;
import org.apache.coyote.BadRequestException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    private final String AVISO_BAD_REQUEST="La solicitud es invalida";
    private final String AVISO_NOT_FOUND="Equipo no encontrado";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().build();
        errorResponse.setCodigo(status.value());
        errorResponse.setMensaje(AVISO_BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = {BadRequestException.class, PropertyValueException.class})
    protected ResponseEntity<ErrorResponse> handleConflict() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = ErrorResponse.builder().build();
        errorResponse.setCodigo(status.value());
        errorResponse.setMensaje(AVISO_BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    protected ResponseEntity<ErrorResponse> handleConflictNotFound(
            NoSuchElementException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = ErrorResponse.builder().build();
        errorResponse.setCodigo(status.value());
        errorResponse.setMensaje(AVISO_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, status);
    }
}