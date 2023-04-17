package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.ProcessorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorsRepository extends CommonRepository<ProcessorEntity> {

    default Class<ProcessorEntity> getType() {
        return ProcessorEntity.class;
    }
}
