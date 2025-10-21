package com.example.crmsystem.controller;

import com.example.crmsystem.dto.TaskRequestDTO;
import com.example.crmsystem.dto.TaskResponseDTO;
import com.example.crmsystem.model.TaskPriority;
import com.example.crmsystem.model.TaskStatus;
import com.example.crmsystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "APIs for managing tasks in CRM system")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create new task", description = "Create a new task in the system")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.createTask(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Update existing task by ID")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.updateTask(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Retrieve a specific task by ID")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        TaskResponseDTO response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks from the system")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get tasks by status", description = "Retrieve tasks filtered by status")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskResponseDTO> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority/{priority}")
    @Operation(summary = "Get tasks by priority", description = "Retrieve tasks filtered by priority")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByPriority(@PathVariable TaskPriority priority) {
        List<TaskResponseDTO> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get tasks by customer", description = "Retrieve all tasks for a specific customer")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByCustomerId(@PathVariable Long customerId) {
        List<TaskResponseDTO> tasks = taskService.getTasksByCustomerId(customerId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/overdue")
    @Operation(summary = "Get overdue tasks", description = "Retrieve all overdue tasks")
    public ResponseEntity<List<TaskResponseDTO>> getOverdueTasks() {
        List<TaskResponseDTO> tasks = taskService.getOverdueTasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Delete a task from the system by ID")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
