package com.tradeautomizer.mappers;

import com.tradeautomizer.dto.MotherboarDto;
import com.tradeautomizer.dto.ProcessorDto;
import com.tradeautomizer.entities.MotherboardEntity;
import com.tradeautomizer.entities.ProcessorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    ProcessorEntity map(ProcessorDto dao);

    ProcessorDto map(ProcessorEntity entity);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    MotherboardEntity map(MotherboarDto dao);

    MotherboarDto map(MotherboardEntity entity);
}
