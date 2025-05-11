package com.example.demo.DTO.mapper;

import com.example.demo.DTO.PriceDto;
import com.example.demo.model.Price;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {
    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Price partialUpdate(PriceDto priceDto, @MappingTarget Price price);
}
