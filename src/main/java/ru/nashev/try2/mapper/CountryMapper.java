package ru.nashev.try2.mapper;

import org.mapstruct.Mapper;
import ru.nashev.try2.dto.CountryDTO;
import ru.nashev.try2.model.Country;

/**
 * Объявление для генерации через mapstruct маппера страны на DTO
 * @author Nashev
 */
@Mapper(componentModel = "spring")
public interface CountryMapper extends GenericListMapper<Country, CountryDTO> {
}
