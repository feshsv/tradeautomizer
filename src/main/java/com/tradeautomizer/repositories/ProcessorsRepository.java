package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.ProcessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorsRepository extends JpaRepository<ProcessorEntity, String> {
}
