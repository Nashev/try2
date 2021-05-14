package ru.nashev.try2.dao;

import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Doc;

/**
 * Репозиторий для загрузки типов документов из базы
 * @author Nashev
 */
@Repository
public interface DocRepository extends GenericRepository<Doc> {

    /**
     для маппинга
     пока он нормально не поддерживает Optional, Optional здесь не используется.
     */
    Doc getByCode(String code);
}
