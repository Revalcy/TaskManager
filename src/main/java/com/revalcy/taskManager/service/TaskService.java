package com.revalcy.taskManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.revalcy.taskManager.dto.UpdateTaskRequest;
import com.revalcy.taskManager.exception.TaskNotFoundException;
import com.revalcy.taskManager.model.Task;
import com.revalcy.taskManager.repository.TaskRepository;

@Service 
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 4L;

    public List<Task> getAllTasks(Boolean completed){
        if(completed == null){
            return taskRepository.findAll();
        } 

        return taskRepository.findByCompleted(completed);
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(String title, String description){
        Task newTask = new Task(title, description);
        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, UpdateTaskRequest request){
        Task task = getTaskById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.getCompleted());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
