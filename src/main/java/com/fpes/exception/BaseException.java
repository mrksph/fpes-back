package com.fpes.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class BaseException extends RuntimeException {

    protected List<String> errors;

    public BaseException(List<String> errors) {
        super();
        this.errors = errors;
    }

    public BaseException(String message) {
        super(message);
        this.errors = Collections.singletonList(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException() {

    }
}
