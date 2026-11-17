package com.revalcy.taskManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revalcy.taskManager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}
