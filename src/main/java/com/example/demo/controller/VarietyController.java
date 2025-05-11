package com.example.demo.controller;

import com.example.demo.DTO.VarietyDtoRequest;
import com.example.demo.DTO.VarietyDtoResponse;
import com.example.demo.service.VarietyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/variety")
public class VarietyController {
    private final VarietyService varietyService;

    @Operation(summary = "getAllVariety")
    @GetMapping
    public ResponseEntity<List<VarietyDtoResponse>> getAllVariety(){
        return ResponseEntity.status(HttpStatus.OK).body(varietyService.findAllVariety());
    }

    @Operation(summary = "getVarietyById")
    @GetMapping("/{id}")
    public VarietyDtoResponse getVarietyById(@PathVariable Long id){
        return varietyService.findByIdVariety(id);
    }

    @Operation(summary = "createVariety")
    @PostMapping
    public VarietyDtoResponse createVariety(@RequestBody VarietyDtoRequest variety){
        return varietyService.createVariety(variety);
    }

    @Operation(summary = "updateVariety")
    @PutMapping("/{id}")
    public VarietyDtoResponse updateVariety(@PathVariable Long id, @RequestBody VarietyDtoRequest variety){
        return varietyService.updateVariety(id, variety);
    }

    @Operation(summary = "deleteVariety")
    @DeleteMapping("/{id}")
    public void deleteVariety(@PathVariable Long id){
        varietyService.deleteByIdVariety(id);
    }
}
