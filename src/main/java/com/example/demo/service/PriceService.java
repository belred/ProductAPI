package com.example.demo.service;

import com.example.demo.DTO.PriceDto;
import com.example.demo.DTO.mapper.PriceMapper;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PriceDto findByIdPrice(Long id){
        var price = priceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует товара с id: %d".formatted(id)));
        return priceMapper.toDto(price);
    }

    public PriceDto updatePrice(Long id, PriceDto priceDetails){
        Price price = priceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует товара с id: %d".formatted(id)));

        priceMapper.partialUpdate(priceDetails, price);

        return priceMapper.toDto(priceRepository.save(price));
    }
}
