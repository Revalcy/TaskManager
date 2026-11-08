package com.revalcy.taskManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revalcy.taskManager.dto.UpdateTaskRequest;
import com.revalcy.taskManager.exception.TaskNotFoundException;
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

    public List<Task> getAllTasks(Boolean completed){
        List<Task> filterTask = new ArrayList<>();
        if(completed == null){
            return tasks;
        } else {
            for(Task task : tasks){
                if(task.isCompleted() == completed){
                    filterTask.add(task);
                }
            }
        }

        return filterTask;
    }

    public Task getTaskById(Long id){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }

        throw new TaskNotFoundException(id);
    }

    public Task createTask(String title, String description){
        Task newTask = new Task(nextId, title, description);
        tasks.add(newTask);
        nextId++;
        return newTask;
    }

    public Task updateTask(Long id, UpdateTaskRequest request){
        for(Task task : tasks){
            if(task.getId().equals(id)){
                task.setTitle(request.getTitle());
                task.setDescription(request.getDescription());
                task.setCompleted(request.getCompleted());

                return task;
            }
        }

        throw new TaskNotFoundException(id);
    }

    public void deleteTask(Long id){
        boolean removed = tasks.removeIf(task -> task.getId().equals(id));

        if(!removed){
            throw new TaskNotFoundException(id);
        }
    }
}
