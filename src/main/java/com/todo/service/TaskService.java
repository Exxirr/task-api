package com.todo.service;

import com.todo.entity.Task;

import java.util.List;

public interface TaskService {

    //CRUD CREATE READ UPDATE DELETE

    List<Task> getAllTasks();

    List<Task> getCompletedTasks();

    Task getTaskById(Long id);

    Task patchTaskCompleted(Long id, boolean completed);

    Task createTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

}
