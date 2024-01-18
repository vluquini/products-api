package com.project.products.exceptions;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ErrorMessage {
    private Instant timestamp;
    private HttpStatus httpStatus;
    private String error;
    private String message;
    private String path;

    public ErrorMessage(Instant timestamp, HttpStatus httpStatus, String error, String message, String path) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
