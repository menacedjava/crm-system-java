package com.example.crmsystem.repository;

import com.example.crmsystem.model.Task;
import com.example.crmsystem.model.TaskPriority;
import com.example.crmsystem.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByCustomerId(Long customerId);

    List<Task> findByDueDateBefore(LocalDateTime dueDate);

    @Query("SELECT t FROM Task t WHERE t.status = ?1 AND t.priority = ?2")
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);

    @Query("SELECT t FROM Task t WHERE t.customer.id = ?1 AND t.status = ?2")
    List<Task> findByCustomerIdAndStatus(Long customerId, TaskStatus status);
}
