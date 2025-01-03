package com.ssaisriharsha.RestApi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IllegalOperationResponse {
    private int status;
    private String response;
    private long timestamp;
}
