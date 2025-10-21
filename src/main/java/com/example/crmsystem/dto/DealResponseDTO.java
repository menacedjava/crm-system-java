package com.example.crmsystem.dto;

import com.example.crmsystem.model.DealStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private DealStage stage;
    private LocalDate expectedCloseDate;
    private LocalDate actualCloseDate;
    private Long customerId;
    private String customerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
