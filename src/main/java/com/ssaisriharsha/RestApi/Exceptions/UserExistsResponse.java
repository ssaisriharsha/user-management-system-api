package com.ssaisriharsha.RestApi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserExistsResponse {
    private int status;
    private String response;
    private long timestamp;
}
