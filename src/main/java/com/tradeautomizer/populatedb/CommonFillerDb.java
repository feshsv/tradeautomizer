package com.tradeautomizer.populatedb;

import com.tradeautomizer.dao.Daos;
import com.tradeautomizer.dao.MotherboardDao;
import com.tradeautomizer.dao.ProcessorDao;
import com.tradeautomizer.mappers.CommonMapper;
import com.tradeautomizer.repository.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommonFillerDb {

    private final RepositoryFactory repositoryFactory;
    private final CommonMapper commonMapper;

    @Autowired
    public CommonFillerDb(RepositoryFactory repositoryFactory,
                          CommonMapper commonMapper) {
        this.repositoryFactory = repositoryFactory;
        this.commonMapper = commonMapper;
    }

    public void fillDb() {
        Map<Daos, String> daoTypeAndCsvFilePath = new HashMap<>();
        daoTypeAndCsvFilePath.put(ProcessorDao.builder().build(), "C:\\Users\\Sergey\\Downloads\\Закупки\\processors.csv");
        daoTypeAndCsvFilePath.put(MotherboardDao.builder().build(), "C:\\Users\\Sergey\\Downloads\\Закупки\\motherboards.csv");

        for (Map.Entry<Daos, String> pair : daoTypeAndCsvFilePath.entrySet()) {
            if(pair.getKey() instanceof ProcessorDao) {
                var entities = CsvToDaos.getDaos((ProcessorDao) pair.getKey(), pair.getValue())
                        .stream()
                        .map(commonMapper::map)
                        .collect(Collectors.toList());
                var repository = repositoryFactory.getRepository(pair.getKey());
                entities.forEach(repository::save);
                //repository.findAll().forEach(System.out::println);
            } else if(pair.getKey() instanceof MotherboardDao) {
                var entities = CsvToDaos.getDaos((MotherboardDao) pair.getKey(), pair.getValue())
                        .stream()
                        .map(commonMapper::map)
                        .collect(Collectors.toList());
                var repository = repositoryFactory.getRepository(pair.getKey());
                entities.forEach(repository::save);
                //repository.findAll().forEach(System.out::println);
            }
        }
    }
}
