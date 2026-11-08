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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revalcy.taskManager.dto.CreateTaskRequest;
import com.revalcy.taskManager.dto.UpdateTaskRequest;
import com.revalcy.taskManager.model.Task;
import com.revalcy.taskManager.service.TaskService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello, Task Manager!";
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
        @RequestParam(required = false) Boolean completed
    ){
        List<Task> allTasks = taskService.getAllTasks(completed);
        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task getTask = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getTask);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskRequest request){
        Task newTask = taskService.createTask(request.getTitle(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
        @PathVariable Long id, 
        @Valid @RequestBody UpdateTaskRequest request){
        Task updateNewTask = taskService.updateTask(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updateNewTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
