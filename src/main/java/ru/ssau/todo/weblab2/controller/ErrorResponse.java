package ru.ssau.todo.weblab2.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String errorMessage;
    private int status;
    
    public ErrorResponse(String errorMessage, int status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
