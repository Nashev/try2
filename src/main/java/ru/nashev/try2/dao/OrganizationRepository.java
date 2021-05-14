package ru.nashev.try2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Organization;

/**
 * Репозиторий для загрузки организаций из базы и добавления новых
 * Поддерживает загрузку с динамической фильтрацией через JpaSpecificationExecutor
 * @author Nashev
 */
@Repository
public interface OrganizationRepository extends GenericRepository<Organization>, JpaSpecificationExecutor<Organization> {

    /**
     для маппинга
     пока он нормально не поддерживает Optional, мапингу делаю аналог базового FindById но без Optional.
     */
    Organization getById(Long id);
}
