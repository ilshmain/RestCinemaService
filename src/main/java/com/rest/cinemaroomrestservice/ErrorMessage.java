package com.rest.cinemaroomrestservice;

public class ErrorMessage {
    private String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public ErrorMessage() {
    }

    public void setError(String error) {
        this.error = error;
    }
}
