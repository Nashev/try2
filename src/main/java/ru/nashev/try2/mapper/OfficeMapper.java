package ru.nashev.try2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.nashev.try2.dao.OrganizationRepository;
import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;
import ru.nashev.try2.model.Office;

/**
 * Объявление для генерации через mapstruct маппера офиса на DTO и обратно
 * @author Nashev
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                OrganizationRepository.class
        }
)
public interface OfficeMapper extends GenericMultiMapper<Office, OfficeGetDTO, OfficeListDTO, OfficeAddDTO, OfficeUpdateDTO> {

    @Mapping(target = "organization", source = "orgId")
    Office add(OfficeAddDTO dto);
}