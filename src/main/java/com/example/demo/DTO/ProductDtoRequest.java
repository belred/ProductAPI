package com.example.demo.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ProductDtoRequest {
    @NotBlank
    String name;
    String description;
    @Valid
    PriceDto price;
}
