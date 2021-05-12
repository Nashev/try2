package ru.nashev.try2.service;


import ru.nashev.try2.dto.organization.OrganizationAddDTO;
import ru.nashev.try2.dto.organization.OrganizationFilter;
import ru.nashev.try2.dto.organization.OrganizationGetDTO;
import ru.nashev.try2.dto.organization.OrganizationListDTO;
import ru.nashev.try2.dto.organization.OrganizationUpdateDTO;

public interface OrganizationService extends GenericService<OrganizationGetDTO, OrganizationListDTO, OrganizationFilter, OrganizationAddDTO, OrganizationUpdateDTO> {
}