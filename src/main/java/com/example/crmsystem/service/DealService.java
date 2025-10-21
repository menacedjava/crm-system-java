package com.example.crmsystem.service;

import com.example.crmsystem.dto.DealRequestDTO;
import com.example.crmsystem.dto.DealResponseDTO;
import com.example.crmsystem.model.DealStage;

import java.math.BigDecimal;
import java.util.List;

public interface DealService {
    DealResponseDTO createDeal(DealRequestDTO requestDTO);

    DealResponseDTO updateDeal(Long id, DealRequestDTO requestDTO);

    DealResponseDTO getDealById(Long id);

    List<DealResponseDTO> getAllDeals();

    List<DealResponseDTO> getDealsByStage(DealStage stage);

    List<DealResponseDTO> getDealsByCustomerId(Long customerId);

    BigDecimal getTotalAmountByStage(DealStage stage);

    void deleteDeal(Long id);
}
