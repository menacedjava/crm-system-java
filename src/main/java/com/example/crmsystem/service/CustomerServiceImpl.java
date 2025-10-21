package com.example.crmsystem.service;

import com.example.crmsystem.dto.CustomerRequestDTO;
import com.example.crmsystem.dto.CustomerResponseDTO;
import com.example.crmsystem.exeption.ResourceNotFoundException;
import com.example.crmsystem.model.Customer;
import com.example.crmsystem.model.CustomerStatus;
import com.example.crmsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO) {
        // Email mavjudligini tekshirish
        if (customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDTO.getEmail());
        }

        Customer customer = new Customer();
        customer.setFirstName(requestDTO.getFirstName());
        customer.setLastName(requestDTO.getLastName());
        customer.setEmail(requestDTO.getEmail());
        customer.setPhone(requestDTO.getPhone());
        customer.setCompany(requestDTO.getCompany());
        customer.setAddress(requestDTO.getAddress());
        customer.setStatus(requestDTO.getStatus());

        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        // Email o'zgargan bo'lsa va boshqa customer'da mavjud bo'lsa
        if (!customer.getEmail().equals(requestDTO.getEmail()) &&
                customerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDTO.getEmail());
        }

        customer.setFirstName(requestDTO.getFirstName());
        customer.setLastName(requestDTO.getLastName());
        customer.setEmail(requestDTO.getEmail());
        customer.setPhone(requestDTO.getPhone());
        customer.setCompany(requestDTO.getCompany());
        customer.setAddress(requestDTO.getAddress());
        customer.setStatus(requestDTO.getStatus());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToResponseDTO(updatedCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToResponseDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersByStatus(CustomerStatus status) {
        return customerRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> searchCustomersByName(String name) {
        return customerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }

    // Helper method - Entity ni DTO ga o'girish
    private CustomerResponseDTO mapToResponseDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setCompany(customer.getCompany());
        dto.setAddress(customer.getAddress());
        dto.setStatus(customer.getStatus());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }

}
