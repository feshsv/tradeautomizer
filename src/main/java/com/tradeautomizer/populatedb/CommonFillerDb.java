package com.tradeautomizer.populatedb;

import com.tradeautomizer.entities.AbstractEntity;
import com.tradeautomizer.entities.MotherboardEntity;
import com.tradeautomizer.entities.ProcessorEntity;
import com.tradeautomizer.repositories.MotherboardRepository;
import com.tradeautomizer.repositories.ProcessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
public class CommonFillerDb {

    private final MotherboardRepository motherboardRepository;
    private final ProcessorsRepository processorsRepository;

    @Autowired
    public CommonFillerDb(MotherboardRepository motherboardRepository, ProcessorsRepository processorsRepository) {
        this.motherboardRepository = motherboardRepository;
        this.processorsRepository = processorsRepository;
    }

    public void initDb() {
        loadDb("src\\main\\resources\\source\\processors.csv", ObjectFactory::createProcessorEntity);
        loadDb("src\\main\\resources\\source\\motherboards.csv", ObjectFactory::createMotherboarEntity);
    }

    public <T extends AbstractEntity> void loadDb(String path, Function<String, T> arrayObj) {
        Collection<String> lines = CsvToDtos.readCsv(path);
        for(String line : lines) {
            if (line.contains("model") ||
            line.contains("core") ||
            line.contains("flow") ||
            line.contains("base_frequency")) {
                return;
            }
            T someEntity = arrayObj.apply(line);
            if (someEntity instanceof MotherboardEntity) {
                motherboardRepository.save((MotherboardEntity) someEntity);
            }
            if (someEntity instanceof ProcessorEntity) {
                processorsRepository.save((ProcessorEntity) someEntity);
            }
        }
    }
}
