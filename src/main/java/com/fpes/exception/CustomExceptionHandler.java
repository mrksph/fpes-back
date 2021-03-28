package com.fpes.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = JWTException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<HttpError> handleConflict(JWTException exception, HttpServletRequest request) {
        String reason = "Failed to read JWT token from request headers.";
        return handleHttpError(exception, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<HttpError> handleConflict(EntityNotFoundException exception, HttpServletRequest request) {
        String reason = "Error while finding entity";
        return handleHttpError(exception, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = EntityNotActiveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<HttpError> handleConflict(EntityNotActiveException exception, HttpServletRequest request) {
        String reason = "Entity Not Active Exception";
        return handleHttpError(exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<HttpError> handleConflict(ValidationException exception, HttpServletRequest request) {
        String reason = "Validation Exception";
        return handleHttpError(exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<HttpError> handleHttpError(BaseException exception,
                                                      HttpHeaders headers,
                                                      HttpStatus status,
                                                      HttpServletRequest request) {
        HttpError error = new HttpError();
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());

        error.setTimestamp(formattedDate);
        error.setStatus(status.value());
        error.setMessage(status.getReasonPhrase());
        error.setErrors(exception.getErrors());
        error.setPath(request.getRequestURI());
        return new ResponseEntity<>(error, headers, status);
    }
}
