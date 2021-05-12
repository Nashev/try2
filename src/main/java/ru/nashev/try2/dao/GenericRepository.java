package ru.nashev.try2.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<Entity> extends Repository<Entity, Long> {
    Optional<Entity> findById(Long id);
    Iterable<Entity> findAll();
    void save(Entity entity);
}
