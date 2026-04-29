package com.todo.service.impl;

import com.todo.exception.ResourceNotFoundException;
import com.todo.entity.Task;
import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompletedTrue();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));
    }

    @Override
    public Task patchTaskCompleted(Long id, boolean completed) {

        Task taskExisting = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));
        taskExisting.setCompleted(completed);

        return taskRepository.save(taskExisting);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {

        Task taskExists = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with Id " + id));

        taskExists.setTitle(task.getTitle());
        taskExists.setDescription(task.getDescription());
        taskExists.setCompleted(task.getCompleted());

        return taskRepository.save(taskExists);
    }

    @Override
    public void deleteTask(Long id) {

        if(!taskRepository.existsById(id)){
            throw new RuntimeException("Task not found");
        }

        taskRepository.deleteById(id);

    }
}
