package com.example.crmsystem.repository;

import com.example.crmsystem.model.Customer;
import com.example.crmsystem.model.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Email bo'yicha qidirish
    Optional<Customer> findByEmail(String email);

    // Email mavjudligini tekshirish
    boolean existsByEmail(String email);

    // Status bo'yicha filter qilish
    List<Customer> findByStatus(CustomerStatus status);

    // Company bo'yicha qidirish
    List<Customer> findByCompanyContainingIgnoreCase(String company);

    // Ism bo'yicha qidirish
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);


}
