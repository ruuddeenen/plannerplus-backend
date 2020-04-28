package com.ruuddeenen.plannerplus.exceptions;

public class TimeOutOfRangeException extends Exception {
    public TimeOutOfRangeException(String message){
        super(message);
    }

    public TimeOutOfRangeException(String message, Throwable throwable){
        super(message, throwable);
    }
}
