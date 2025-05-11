package com.example.demo.controller;

import com.example.demo.DTO.PriceDto;
import com.example.demo.DTO.ProductDtoRequest;
import com.example.demo.DTO.ProductDtoResponse;
import com.example.demo.DTO.VarietyDtoResponse;
import com.example.demo.service.PriceService;
import com.example.demo.service.ProductService;
import com.example.demo.service.VarietyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final PriceService priceService;
    private final VarietyService varietyService;

    @Operation(summary = "getAllProducts")
    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @Operation(summary = "getProductById")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @Operation(summary = "createProduct")
    @PostMapping
    public ResponseEntity<ProductDtoResponse> createProduct(@RequestBody ProductDtoRequest product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @Operation(summary = "updateProduct")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDtoRequest product){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, product));
    }

    @Operation(summary = "deleteProduct")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "getPriceByIdProduct")
    @GetMapping("/{id}/price")
    public ResponseEntity<PriceDto> getPriceByIdProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(priceService.findByIdPrice(id));
    }
}
