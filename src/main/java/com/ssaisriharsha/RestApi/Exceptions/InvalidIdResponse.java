package com.ssaisriharsha.RestApi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidIdResponse {
    private final int status;
    private final String message="ID must be an integer.";
    private final long timestamp;
}
