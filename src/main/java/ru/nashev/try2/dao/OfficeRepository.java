package ru.nashev.try2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Office;

@Repository
public interface OfficeRepository extends GenericRepository<Office>, JpaSpecificationExecutor<Office> {
}