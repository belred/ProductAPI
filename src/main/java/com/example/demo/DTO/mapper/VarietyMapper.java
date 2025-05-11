package com.example.demo.DTO.mapper;

import com.example.demo.DTO.VarietyDtoRequest;
import com.example.demo.model.Variety;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VarietyMapper {
    Variety toEntity(VarietyDtoRequest varietyDto);

    //VarietyDtoResponse toDto(Variety variety);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Variety partialUpdate(VarietyDtoRequest varietyDto, @MappingTarget Variety variety);
}
