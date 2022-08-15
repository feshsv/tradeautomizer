package com.tradeautomizer.repository;

import com.tradeautomizer.dao.Daos;
import com.tradeautomizer.dao.MotherboardDao;
import com.tradeautomizer.dao.ProcessorDao;
import com.tradeautomizer.entity.AbstractEntity;
import com.tradeautomizer.entity.MotherboardEntity;
import com.tradeautomizer.entity.ProcessorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RepositoryFactory {

    private final CommonRepository<ProcessorEntity> processorEntityCommonRepository;
    private final CommonRepository<MotherboardEntity> motherboardEntityCommonRepository;

    @Autowired
    public RepositoryFactory(CommonRepository<ProcessorEntity> processorEntityCommonRepository,
                             CommonRepository<MotherboardEntity> motherboardEntityCommonRepository) {
        this.processorEntityCommonRepository = processorEntityCommonRepository;
        this.motherboardEntityCommonRepository = motherboardEntityCommonRepository;
    }

    public <T extends AbstractEntity> CommonRepository<T> getRepository(Daos entityType) {
        Map<Daos, CommonRepository<? extends AbstractEntity>> repositoryAbstractEntityMap = new HashMap<>();
        repositoryAbstractEntityMap.put(ProcessorDao.builder().build(), processorEntityCommonRepository);
        repositoryAbstractEntityMap.put(MotherboardDao.builder().build(), motherboardEntityCommonRepository);
        return (CommonRepository<T>) repositoryAbstractEntityMap.get(entityType);
    }
}
