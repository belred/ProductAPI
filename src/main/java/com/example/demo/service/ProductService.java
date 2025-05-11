package com.example.demo.service;

import com.example.demo.DTO.ProductDtoRequest;
import com.example.demo.DTO.ProductDtoResponse;
import com.example.demo.DTO.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDtoResponse createProduct(ProductDtoRequest product){
        var productSave = productRepository.save(productMapper.toEntity(product));
        return productMapper.toDto(productSave);
    }

    public ProductDtoResponse findById(Long id){
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует товара с id: %d".formatted(id)));
        return productMapper.toDto(product);
    }

    public List<ProductDtoResponse> findAll(){
        var products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).toList();
    }

    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDetails){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует товара с id: %d".formatted(id)));

        productMapper.partialUpdate(productDetails, product);

        return productMapper.toDto(productRepository.save(product));
    }

    public void deleteById(Long id){
        if (!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Не существует товара с id: %d".formatted(id));
        }
        productRepository.deleteById(id);
    }
}
