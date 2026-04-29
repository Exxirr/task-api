package com.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Boolean completed;

}
