package ru.nashev.try2.service;

import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeFilter;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;

/**
 * Интерфейс сервиса для работы с офисами
 * @author Nashev
 */
public interface OfficeService extends GenericMultiService<OfficeGetDTO, OfficeListDTO, OfficeFilter, OfficeAddDTO, OfficeUpdateDTO> {
}