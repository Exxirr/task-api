package com.todo.service.impl;

import com.todo.dto.TaskRequest;
import com.todo.dto.TaskResponse;
import com.todo.exception.ResourceNotFoundException;
import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        List<Task> tasks = taskRepository.findAll();

        List<TaskResponse> responses = new ArrayList<>();

        for(Task task : tasks){
            responses.add(mapToResponse(task));
        }

        return responses;
    }

    @Override
    public List<TaskResponse> getCompletedTasks() {

        List<Task> tasks = taskRepository.findByCompletedTrue();

        List<TaskResponse> responses = new ArrayList<>();

        for(Task task : tasks){
            responses.add(mapToResponse(task));
        }

        return responses;
    }

    @Override
    public TaskResponse getTaskById(Long id) {

        Task task =  taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));

        return mapToResponse(task);
    }

    @Override
    public TaskResponse patchTaskCompleted(Long id, boolean completed) {

        Task taskExisting = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));
        taskExisting.setCompleted(completed);

        Task task =  taskRepository.save(taskExisting);

        return mapToResponse(task);
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.getCompleted());

        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {

        Task taskExists = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));

        taskExists.setTitle(taskRequest.getTitle());
        taskExists.setDescription(taskRequest.getDescription());
        taskExists.setCompleted(taskRequest.getCompleted());

        Task update =  taskRepository.save(taskExists);

        return mapToResponse(update);

    }

    @Override
    public void deleteTask(Long id) {

        if(!taskRepository.existsById(id)){
            throw new ResourceNotFoundException("Task not found with Id : " + id);
        }

        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task){

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.getCompleted());

        return response;
    }

}
