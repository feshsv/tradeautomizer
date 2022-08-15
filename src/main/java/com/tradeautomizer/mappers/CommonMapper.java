package com.tradeautomizer.mappers;

import com.tradeautomizer.dao.MotherboardDao;
import com.tradeautomizer.dao.ProcessorDao;
import com.tradeautomizer.entity.MotherboardEntity;
import com.tradeautomizer.entity.ProcessorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    ProcessorEntity map(ProcessorDao dao);

    ProcessorDao map(ProcessorEntity entity);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    MotherboardEntity map(MotherboardDao dao);

    MotherboardDao map(MotherboardEntity entity);
}
