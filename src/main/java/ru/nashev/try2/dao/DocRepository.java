package ru.nashev.try2.dao;

import org.springframework.stereotype.Repository;
import ru.nashev.try2.model.Doc;

@Repository
public interface DocRepository extends GenericRepository<Doc> {
    /**
        для маппинга, он пока не поддерживает Optional
     */
    Doc getByCode(String code);
}
