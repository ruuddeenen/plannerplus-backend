package com.ruuddeenen.plannerplus.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record not found.")
public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String message){
        super(message);
    }

    public RecordNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
