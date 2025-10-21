package com.example.crmsystem.controller;

import com.example.crmsystem.dto.CustomerRequestDTO;
import com.example.crmsystem.dto.CustomerResponseDTO;
import com.example.crmsystem.model.CustomerStatus;
import com.example.crmsystem.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers in CRM system")

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create new customer", description = "Create a new customer in the system")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO response = customerService.createCustomer(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Update existing customer by ID")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO response = customerService.updateCustomer(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieve a specific customer by their ID")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieve all customers from the system")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get customers by status", description = "Retrieve customers filtered by their status")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersByStatus(@PathVariable CustomerStatus status) {
        List<CustomerResponseDTO> customers = customerService.getCustomersByStatus(status);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/search")
    @Operation(summary = "Search customers by name", description = "Search customers by first name or last name")
    public ResponseEntity<List<CustomerResponseDTO>> searchCustomersByName(@RequestParam String name) {
        List<CustomerResponseDTO> customers = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Delete a customer from the system by ID")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
