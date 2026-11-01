package com.revalcy.taskManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.revalcy.taskManager.model.Task;

@Service 
public class TaskService {
    
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 4L;

    public TaskService() {
        this.tasks.add(new Task(1L,"Study Java", "Learn Spring Boot"));
        this.tasks.add(new Task(2L, "Completed Homework", "Finish Data Structure"));
        this.tasks.add(new Task(3L, "Exercise", "Go Swimming"));
    }

    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task getTaskById(Long id){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not Found");
    }

    public Task createTask(String title, String description){
        Task newTask = new Task(nextId, title, description);
        tasks.add(newTask);
        nextId++;
        return newTask;
    }
}
