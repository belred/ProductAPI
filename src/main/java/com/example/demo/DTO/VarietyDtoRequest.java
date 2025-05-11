package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class VarietyDtoRequest {
    @NotBlank
    private String type;
    @NotNull
    private Long product_Id;
}
