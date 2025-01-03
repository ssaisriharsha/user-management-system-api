package com.ssaisriharsha.RestApi.Exceptions;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class UserNotFoundResponse {
    private final int status;
    private final String message;
    private final long timestamp;

    public UserNotFoundResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}
