package com.todo.service;

import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.entity.Task;

import java.util.List;

public interface TaskService {

    //CRUD CREATE READ UPDATE DELETE

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getCompletedTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse patchTaskCompleted(Long id, boolean completed);

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);

}
