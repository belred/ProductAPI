package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class PriceDto {
    @NotBlank
    private int price;
}
