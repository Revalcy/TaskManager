package com.revalcy.taskManager.dto; 

import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.Size; 

public class CreateTaskRequest { 
    @NotBlank(message = "Title is required and cannot be empty") 
    @Size(max = 100, message = "Title cannot exceed 100 characters") 
    
    private String title; 
    private String description; 
    
    public CreateTaskRequest() {} 
    public CreateTaskRequest(String title, String description) { 
        this.title = title; 
        this.description = description; 
    } 
    
    public String getTitle() { 
        return title; 
    } 
    
    public void setTitle(String title) { 
        this.title = title; 
    } 
    
    public String getDescription() { 
        return description; 
    } 
    
    public void setDescription(String description) { 
        this.description = description; 
    } 
}