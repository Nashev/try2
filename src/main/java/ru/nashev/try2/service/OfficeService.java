package ru.nashev.try2.service;

import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeFilter;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;

public interface OfficeService extends GenericService<OfficeGetDTO, OfficeListDTO, OfficeFilter, OfficeAddDTO, OfficeUpdateDTO> {
}