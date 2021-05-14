package ru.nashev.try2.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Базовый интерфейс для генерации репозиториев, позволяющих получать из базы полный список объектов,
 * получать объект из базы по ID и добавить новый объект в базу.
 * @param <E> - сущность, которую будет обрабатывать репозиторий
 * @author Nashev
 */
@NoRepositoryBean
public interface GenericRepository<E> extends Repository<E, Long> {
    Optional<E> findById(Long id);
    Iterable<E> findAll();
    void save(E e);
}
