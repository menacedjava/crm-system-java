package com.example.crmsystem.controller;

import com.example.crmsystem.dto.DealRequestDTO;
import com.example.crmsystem.dto.DealResponseDTO;
import com.example.crmsystem.model.DealStage;
import com.example.crmsystem.service.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
@Tag(name = "Deal Management", description = "APIs for managing deals in CRM system")

public class DealController {
    private final DealService dealService;

    @PostMapping
    @Operation(summary = "Create new deal", description = "Create a new deal in the system")
    public ResponseEntity<DealResponseDTO> createDeal(@Valid @RequestBody DealRequestDTO requestDTO) {
        DealResponseDTO response = dealService.createDeal(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update deal", description = "Update existing deal by ID")
    public ResponseEntity<DealResponseDTO> updateDeal(
            @PathVariable Long id,
            @Valid @RequestBody DealRequestDTO requestDTO) {
        DealResponseDTO response = dealService.updateDeal(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get deal by ID", description = "Retrieve a specific deal by ID")
    public ResponseEntity<DealResponseDTO> getDealById(@PathVariable Long id) {
        DealResponseDTO response = dealService.getDealById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all deals", description = "Retrieve all deals from the system")
    public ResponseEntity<List<DealResponseDTO>> getAllDeals() {
        List<DealResponseDTO> deals = dealService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/stage/{stage}")
    @Operation(summary = "Get deals by stage", description = "Retrieve deals filtered by stage")
    public ResponseEntity<List<DealResponseDTO>> getDealsByStage(@PathVariable DealStage stage) {
        List<DealResponseDTO> deals = dealService.getDealsByStage(stage);
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get deals by customer", description = "Retrieve all deals for a specific customer")
    public ResponseEntity<List<DealResponseDTO>> getDealsByCustomerId(@PathVariable Long customerId) {
        List<DealResponseDTO> deals = dealService.getDealsByCustomerId(customerId);
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/total/{stage}")
    @Operation(summary = "Get total amount by stage", description = "Calculate total deal amount for a specific stage")
    public ResponseEntity<BigDecimal> getTotalAmountByStage(@PathVariable DealStage stage) {
        BigDecimal total = dealService.getTotalAmountByStage(stage);
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete deal", description = "Delete a deal from the system by ID")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
