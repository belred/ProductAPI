package com.example.demo.DTO;

import lombok.Value;

import java.util.Set;

@Value
public class ProductDtoResponse {
    String name;
    String description;
    int price;
    Set<String> variety;
}
