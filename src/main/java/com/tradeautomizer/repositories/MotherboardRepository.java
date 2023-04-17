package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.MotherboardEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends CommonRepository<MotherboardEntity> {

    default Class<MotherboardEntity> getType() {
        return MotherboardEntity.class;
    }
}
