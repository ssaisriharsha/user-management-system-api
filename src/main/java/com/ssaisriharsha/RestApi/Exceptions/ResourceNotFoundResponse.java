package com.ssaisriharsha.RestApi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundResponse {
    private final int status=404;
    private final String message="Requested resource is not found on the server.";
    private final long timestamp;
}
