package com.todo.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorMessage {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private Map<String,String> errors;

}
