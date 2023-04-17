package com.tradeautomizer.repositories;

import com.tradeautomizer.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

@NoRepositoryBean
@Component
public interface CommonRepository<E extends AbstractEntity> extends JpaRepository<E, String> {
    Class<E> getType();
}
