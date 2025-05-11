package com.example.demo.DTO.mapper;

import com.example.demo.DTO.ProductDtoRequest;
import com.example.demo.DTO.ProductDtoResponse;
import com.example.demo.model.Price;
import com.example.demo.model.Product;
import com.example.demo.model.Variety;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductDtoRequest productDtoRequest);

    @AfterMapping
    default void linkPrice(@MappingTarget Product product){
        Price price = product.getPrice();
        if (price != null){
            price.setProduct(product);
        }
    }

    @Mapping(target = "price", expression = "java(priceIdsToPrice(product.getPrice()))")
    @Mapping(target = "variety", expression = "java(varietyToType(product.getVariety()))")
    ProductDtoResponse toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductDtoRequest productDtoRequest, @MappingTarget Product product);

    default int priceIdsToPrice(Price price){
       return price.getPrice();
    }

    default Set<String> varietyToType(Set<Variety> variety){
        return variety.stream().map(Variety::getType).collect(Collectors.toSet());
    }
}
