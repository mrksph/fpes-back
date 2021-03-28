package com.fpes.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HttpError {

    private String timestamp;
    private int status;
    private String message;
    private List<String> errors;
    private String path;

}
