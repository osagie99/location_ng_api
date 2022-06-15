package com.surge.locationAPI.LocationAPI.advice;

import com.surge.locationAPI.LocationAPI.exceptions.ExtendedConstants;
import com.surge.locationAPI.LocationAPI.exceptions.NotFoundException;
import com.surge.locationAPI.LocationAPI.helpers.AppResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppResponse> handleInvalidArgs(MethodArgumentNotValidException ex) {
        AppResponse appResponse = new AppResponse();
        Map<String, String> data = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> data.put(error.getField(), error.getDefaultMessage()
                ));

        appResponse.setMessage(ExtendedConstants.ResponseCode.BAD_REQUEST.getDescription());
        appResponse.setStatus(ExtendedConstants.ResponseCode.BAD_REQUEST.getCode());
        appResponse.setData(data);

        return ResponseEntity.ok(
                appResponse
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<AppResponse> handleRequestMissingParams(MissingServletRequestParameterException ex) {
        AppResponse appResponse = new AppResponse();

        appResponse.setMessage(ex.getMessage());
        appResponse.setStatus(ExtendedConstants.ResponseCode.ENTITY_NOT_FOUND.getCode());

        return ResponseEntity.ok(
                appResponse
        );

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AppResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        AppResponse appResponse = new AppResponse();


        appResponse.setMessage(ex.getRootCause().getMessage());
        appResponse.setStatus(ExtendedConstants.ResponseCode.BAD_REQUEST.getCode());

        return ResponseEntity.ok(
                appResponse
        );

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AppResponse> handleBusinessException(NotFoundException ex) {
        AppResponse appResponse = new AppResponse();

        appResponse.setMessage(ex.getMessage());
        appResponse.setStatus(ExtendedConstants.ResponseCode.ENTITY_NOT_FOUND.getCode());

        return ResponseEntity.ok(
                appResponse
        );

    }
}
