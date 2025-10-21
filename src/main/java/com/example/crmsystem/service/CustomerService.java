package com.example.crmsystem.service;

import com.example.crmsystem.dto.CustomerRequestDTO;
import com.example.crmsystem.dto.CustomerResponseDTO;
import com.example.crmsystem.model.CustomerStatus;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);

    CustomerResponseDTO getCustomerById(Long id);

    List<CustomerResponseDTO> getAllCustomers();

    List<CustomerResponseDTO> getCustomersByStatus(CustomerStatus status);

    List<CustomerResponseDTO> searchCustomersByName(String name);

    void deleteCustomer(Long id);
}
