package ru.nashev.try2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.User;

/**
 * Репозиторий для загрузки пользователей из базы и добавления новых
 * Поддерживает загрузку с динамической фильтрацией через JpaSpecificationExecutor
 * @author Nashev
 */
@Repository
public interface UserRepository extends GenericRepository<User>, JpaSpecificationExecutor<User> {
}
