package ru.nashev.try2.mapper;

import org.mapstruct.Mapper;
import ru.nashev.try2.dto.DocDTO;
import ru.nashev.try2.model.Doc;

/**
 * Объявление для генерации через mapstruct маппера типа документа на DTO
 * @author Nashev
 */
@Mapper(componentModel = "spring")
public interface DocMapper extends GenericListMapper<Doc, DocDTO> {
}
