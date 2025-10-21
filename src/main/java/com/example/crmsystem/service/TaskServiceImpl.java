package com.example.crmsystem.service;

import com.example.crmsystem.dto.TaskRequestDTO;
import com.example.crmsystem.dto.TaskResponseDTO;
import com.example.crmsystem.exeption.ResourceNotFoundException;
import com.example.crmsystem.model.Customer;
import com.example.crmsystem.model.Task;
import com.example.crmsystem.model.TaskPriority;
import com.example.crmsystem.model.TaskStatus;
import com.example.crmsystem.repository.CustomerRepository;
import com.example.crmsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final CustomerRepository customerRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
        Task task = new Task();
        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setStatus(requestDTO.getStatus());
        task.setPriority(requestDTO.getPriority());
        task.setDueDate(requestDTO.getDueDate());

        if (requestDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDTO.getCustomerId()));
            task.setCustomer(customer);
        }

        Task savedTask = taskRepository.save(task);
        return mapToResponseDTO(savedTask);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setStatus(requestDTO.getStatus());
        task.setPriority(requestDTO.getPriority());
        task.setDueDate(requestDTO.getDueDate());

        if (requestDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDTO.getCustomerId()));
            task.setCustomer(customer);
        } else {
            task.setCustomer(null);
        }

        Task updatedTask = taskRepository.save(task);
        return mapToResponseDTO(updatedTask);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return mapToResponseDTO(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByCustomerId(Long customerId) {
        return taskRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getOverdueTasks() {
        return taskRepository.findByDueDateBefore(LocalDateTime.now())
                .stream()
                .filter(task -> task.getStatus() != TaskStatus.COMPLETED && task.getStatus() != TaskStatus.CANCELLED)
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        taskRepository.delete(task);
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());

        if (task.getCustomer() != null) {
            dto.setCustomerId(task.getCustomer().getId());
            dto.setCustomerName(task.getCustomer().getFirstName() + " " + task.getCustomer().getLastName());
        }

        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        return dto;
    }
}
