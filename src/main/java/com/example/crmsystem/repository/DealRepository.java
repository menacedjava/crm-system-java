package com.example.crmsystem.repository;

import com.example.crmsystem.model.Deal;
import com.example.crmsystem.model.DealStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findByStage(DealStage stage);

    List<Deal> findByCustomerId(Long customerId);

    List<Deal> findByAmountGreaterThanEqual(BigDecimal amount);

    @Query("SELECT d FROM Deal d WHERE d.customer.id = ?1 AND d.stage = ?2")
    List<Deal> findByCustomerIdAndStage(Long customerId, DealStage stage);

    @Query("SELECT SUM(d.amount) FROM Deal d WHERE d.stage = ?1")
    BigDecimal getTotalAmountByStage(DealStage stage);
}
