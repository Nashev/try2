package ru.nashev.try2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.nashev.try2.dto.organization.OrganizationAddDTO;
import ru.nashev.try2.dto.organization.OrganizationGetDTO;
import ru.nashev.try2.dto.organization.OrganizationListDTO;
import ru.nashev.try2.dto.organization.OrganizationUpdateDTO;
import ru.nashev.try2.model.Organization;

/**
 * Объявление для генерации через mapstruct маппера организации на DTO и обратно
 * @author Nashev
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper extends GenericMultiMapper<Organization, OrganizationGetDTO, OrganizationListDTO, OrganizationAddDTO, OrganizationUpdateDTO> {
}