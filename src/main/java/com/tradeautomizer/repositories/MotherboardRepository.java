package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.MotherboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<MotherboardEntity, String> {
}
