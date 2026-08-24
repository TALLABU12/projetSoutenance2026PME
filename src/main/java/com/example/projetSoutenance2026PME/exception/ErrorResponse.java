package com.example.projetSoutenance2026PME.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timeStame;
    private Map<String,String> errors = new HashMap<>();

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;

    }

    public ErrorResponse() {
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public LocalDateTime getTimeStame() {
        return timeStame;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
