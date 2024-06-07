package com.codegym.payload.response;

public class ForbiddenResponse {

    private String message;

    public ForbiddenResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
