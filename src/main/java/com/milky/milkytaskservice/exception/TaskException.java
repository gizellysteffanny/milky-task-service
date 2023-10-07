package com.milky.milkytaskservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Exception e) {
        super(message, e);
    }
}
