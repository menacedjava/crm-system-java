package com.example.crmsystem.service;

import com.example.crmsystem.dto.TaskRequestDTO;
import com.example.crmsystem.dto.TaskResponseDTO;
import com.example.crmsystem.model.TaskPriority;
import com.example.crmsystem.model.TaskStatus;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(TaskRequestDTO requestDTO);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO);

    TaskResponseDTO getTaskById(Long id);

    List<TaskResponseDTO> getAllTasks();

    List<TaskResponseDTO> getTasksByStatus(TaskStatus status);

    List<TaskResponseDTO> getTasksByPriority(TaskPriority priority);

    List<TaskResponseDTO> getTasksByCustomerId(Long customerId);

    List<TaskResponseDTO> getOverdueTasks();

    void deleteTask(Long id);
}
