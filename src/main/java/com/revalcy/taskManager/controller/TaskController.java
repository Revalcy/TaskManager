package com.revalcy.taskManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task newTask = taskService.createTask(task.getTitle(), task.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updateTask){
        return taskService.updateTask(id, updateTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
