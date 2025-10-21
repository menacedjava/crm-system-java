package com.example.crmsystem.service;

import com.example.crmsystem.dto.DealRequestDTO;
import com.example.crmsystem.dto.DealResponseDTO;
import com.example.crmsystem.exeption.ResourceNotFoundException;
import com.example.crmsystem.model.Customer;
import com.example.crmsystem.model.Deal;
import com.example.crmsystem.model.DealStage;
import com.example.crmsystem.repository.CustomerRepository;
import com.example.crmsystem.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final CustomerRepository customerRepository;

    @Override
    public DealResponseDTO createDeal(DealRequestDTO requestDTO) {
        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDTO.getCustomerId()));

        Deal deal = new Deal();
        deal.setTitle(requestDTO.getTitle());
        deal.setDescription(requestDTO.getDescription());
        deal.setAmount(requestDTO.getAmount());
        deal.setStage(requestDTO.getStage());
        deal.setExpectedCloseDate(requestDTO.getExpectedCloseDate());
        deal.setActualCloseDate(requestDTO.getActualCloseDate());
        deal.setCustomer(customer);

        Deal savedDeal = dealRepository.save(deal);
        return mapToResponseDTO(savedDeal);
    }

    @Override
    public DealResponseDTO updateDeal(Long id, DealRequestDTO requestDTO) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal", "id", id));

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDTO.getCustomerId()));

        deal.setTitle(requestDTO.getTitle());
        deal.setDescription(requestDTO.getDescription());
        deal.setAmount(requestDTO.getAmount());
        deal.setStage(requestDTO.getStage());
        deal.setExpectedCloseDate(requestDTO.getExpectedCloseDate());
        deal.setActualCloseDate(requestDTO.getActualCloseDate());
        deal.setCustomer(customer);

        Deal updatedDeal = dealRepository.save(deal);
        return mapToResponseDTO(updatedDeal);
    }

    @Override
    @Transactional(readOnly = true)
    public DealResponseDTO getDealById(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal", "id", id));
        return mapToResponseDTO(deal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealResponseDTO> getAllDeals() {
        return dealRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealResponseDTO> getDealsByStage(DealStage stage) {
        return dealRepository.findByStage(stage)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealResponseDTO> getDealsByCustomerId(Long customerId) {
        return dealRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalAmountByStage(DealStage stage) {
        BigDecimal total = dealRepository.getTotalAmountByStage(stage);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal", "id", id));
        dealRepository.delete(deal);
    }

    private DealResponseDTO mapToResponseDTO(Deal deal) {
        DealResponseDTO dto = new DealResponseDTO();
        dto.setId(deal.getId());
        dto.setTitle(deal.getTitle());
        dto.setDescription(deal.getDescription());
        dto.setAmount(deal.getAmount());
        dto.setStage(deal.getStage());
        dto.setExpectedCloseDate(deal.getExpectedCloseDate());
        dto.setActualCloseDate(deal.getActualCloseDate());
        dto.setCustomerId(deal.getCustomer().getId());
        dto.setCustomerName(deal.getCustomer().getFirstName() + " " + deal.getCustomer().getLastName());
        dto.setCreatedAt(deal.getCreatedAt());
        dto.setUpdatedAt(deal.getUpdatedAt());
        return dto;
    }
}
