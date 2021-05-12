package ru.nashev.try2.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Organization;

@Repository
public interface OrganizationRepository extends GenericRepository<Organization>, JpaSpecificationExecutor<Organization> {

    /**
     для маппинга, он пока не поддерживает Optional
     */
    Organization getById(Long id);
}