package com.ssaisriharsha.RestApi.Exceptions;

import lombok.Getter;

public class IllegalOperationException extends RuntimeException{
    @Getter
    private final int status;
    public IllegalOperationException(String message, int status) {
        super(message);
        this.status=status;
    }
}
