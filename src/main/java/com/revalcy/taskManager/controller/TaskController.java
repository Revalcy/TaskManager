package com.revalcy.taskManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revalcy.taskManager.model.Task;
import com.revalcy.taskManager.service.TaskService;

@RestController 
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello, Task Manager!";
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task.getTitle(), task.getDescription());
    }
}
