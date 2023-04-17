package com.tradeautomizer.populatedb;

import com.tradeautomizer.entities.AbstractEntity;
import com.tradeautomizer.repositories.CommonRepository;
import com.tradeautomizer.repositories.EntityRepoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
public class CommonFillerDb {

    private final EntityRepoProvider entityRepoProvider;

    @Autowired
    public CommonFillerDb(EntityRepoProvider entityRepoProvider) {
        this.entityRepoProvider = entityRepoProvider;
    }

    public void initDb() {
        loadDb("src\\main\\resources\\source\\processors.csv", ObjectFactory::createProcessorEntity);
        loadDb("src\\main\\resources\\source\\motherboards.csv", ObjectFactory::createMotherboarEntity);
    }

    public <T extends AbstractEntity> void loadDb(String path, Function<String, T> arrayObj) {
        Collection<String> lines = CsvToDtos.readCsv(path);
        String oneLine = lines.stream().reduce((first, second) -> first).orElse(null);
        T apply = arrayObj.apply(oneLine);
        CommonRepository<AbstractEntity> repository1 = entityRepoProvider.getRepository(apply.getClass());
        repository1.save(apply);

        lines.stream()
                .map(arrayObj)
                .forEach(t -> {
                    CommonRepository<AbstractEntity> repository = entityRepoProvider.getRepository(t.getClass());
                    repository.save(t);
                });
    }
}
