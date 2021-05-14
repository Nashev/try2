package ru.nashev.try2.dao;

import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Country;

/**
 * Репозиторий для загрузки стран из базы
 * @author Nashev
 */
@Repository
public interface CountryRepository extends GenericRepository<Country> {

    /**
     для маппинга
     пока он нормально не поддерживает Optional, Optional здесь не используется.
     */
    Country getByCode(String code);
}
