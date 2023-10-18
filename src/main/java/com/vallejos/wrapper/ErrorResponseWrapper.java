package com.vallejos.wrapper;

import com.vallejos.exception.ErrorResponse;

import java.util.List;

public class ErrorResponseWrapper {
    private List<ErrorResponse> error;

    public ErrorResponseWrapper(List<ErrorResponse> error) {
        this.error = error;
    }

    public List<ErrorResponse> getError() {
        return error;
    }
}
