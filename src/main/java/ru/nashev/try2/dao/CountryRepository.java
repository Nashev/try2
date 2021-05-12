package ru.nashev.try2.dao;

import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Country;

@Repository
public interface CountryRepository extends GenericRepository<Country> {

    /**
     для маппинга, он пока не поддерживает Optional
     */
    Country getByCode(String code);
}
