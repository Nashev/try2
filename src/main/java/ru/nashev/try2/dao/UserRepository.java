package ru.nashev.try2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.User;

@Repository
public interface UserRepository extends GenericRepository<User>, JpaSpecificationExecutor<User> {
}