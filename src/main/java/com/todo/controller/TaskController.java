package com.todo.controller;

import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
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
    public List<TaskResponse> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/completed")
    public List<TaskResponse> getAllTasksCompleted(){
        return taskService.getCompletedTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }


    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request){
        return taskService.createTask(request);
    }


    //Todos los REQUEST PARAMS VAN DESPUES DEL ENDPOINT iniciando con "?"
    @PatchMapping("/{id}/completed")
    public TaskResponse updateCompleted(@PathVariable Long id, @RequestParam boolean completed){
        return taskService.patchTaskCompleted(id,completed);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@Valid @RequestBody TaskRequest request, @PathVariable Long id){
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
