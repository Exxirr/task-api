package com.todo.controller;

import com.todo.entity.Task;
import com.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/completed")
    public List<Task> getAllTasksCompleted(){
        return taskService.getCompletedTasks();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }


    @PostMapping
    public Task createTask(@Valid @RequestBody Task task){
        return taskService.createTask(task);
    }


    //Todos los REQUEST PARAMS VAN DESPUES DEL ENDPOINT iniciando con "?"
    @PatchMapping("/{id}/completed")
    public Task updateCompleted(@PathVariable Long id, @RequestParam boolean completed){
        return taskService.patchTaskCompleted(id,completed);
    }

    @PutMapping("/{id}")
    public Task updateTask(@Valid @RequestBody Task task, @PathVariable Long id){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
