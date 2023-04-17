package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntityRepoProvider {

    private final Map<Class, CommonRepository<AbstractEntity>> byType;

    @Autowired
    public EntityRepoProvider(List<CommonRepository<AbstractEntity>> repos) {
        this.byType = repos.stream().collect(Collectors.toMap(CommonRepository::getType, Function.identity()));
    }

    public CommonRepository<AbstractEntity> getRepository(Class entityType) {
        return byType.get(entityType);
    }
}
