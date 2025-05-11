package com.example.demo.service;

import com.example.demo.DTO.VarietyDtoRequest;
import com.example.demo.DTO.VarietyDtoResponse;
import com.example.demo.DTO.mapper.VarietyMapper;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.model.Variety;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.VarietyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class VarietyService {
    private final VarietyRepository varietyRepository;
    private final VarietyMapper varietyMapper;
    private final ProductRepository productRepository;

    public VarietyDtoResponse toDto(Variety variety){
        if (variety == null){
            return null;
        }
        String type = variety.getType();
        Product product = variety.getProduct();
        Long product_id = (product != null) ? product.getId() : null;

        return new VarietyDtoResponse(type, product_id);
    }

    public VarietyDtoResponse createVariety(VarietyDtoRequest variety){
        if (variety == null){
            return null;
        }
        Variety variety1 = new Variety();
        variety1.setType(variety.getType());

        Long productId = variety.getProduct_Id();
        productRepository.findById(productId)
                .ifPresentOrElse(variety1::setProduct,
                        () -> { throw new ResourceNotFoundException("Не существует товара с id: %d".formatted(productId)); });

        return toDto(varietyRepository.save(variety1));
        //var varietySave = varietyRepository.save(varietyMapper.toEntity(variety));
        //return varietyMapper.toDto(varietySave);
    }

    public VarietyDtoResponse findByIdVariety(Long id){
        var variety = varietyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует вкуса с id: %d".formatted(id)));
        return toDto(variety);
    }

    public List<VarietyDtoResponse> findAllVariety(){
        List<Variety> variety = varietyRepository.findAll();
        return varietyRepository.findAll().stream().map(this::toDto).toList();
    }

    public VarietyDtoResponse updateVariety(Long id, VarietyDtoRequest varietyDetails){
        var variety = varietyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не существует вкуса с id: %d".formatted(id)));

        if (varietyDetails.getType() != null){
            variety.setType(varietyDetails.getType());
        }
        if (varietyDetails.getProduct_Id() != null){
            var product = productRepository.findById(varietyDetails.getProduct_Id())
                    .orElseThrow(() -> new ResourceNotFoundException("Не существует товара с id: %d".formatted(id)));
            variety.setProduct(product);
        }

        return toDto(varietyRepository.save(variety));
    }

    public void deleteByIdVariety(Long id){
        if (!varietyRepository.existsById(id)){
            throw new ResourceNotFoundException("Не существует вкуса с id: %d".formatted(id));
        }
        varietyRepository.deleteById(id);
    }
}
