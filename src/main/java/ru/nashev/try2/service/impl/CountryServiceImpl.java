package ru.nashev.try2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nashev.try2.dao.CountryRepository;
import ru.nashev.try2.dto.CountryDTO;
import ru.nashev.try2.mapper.CountryMapper;
import ru.nashev.try2.model.Country;
import ru.nashev.try2.service.CountryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;
    private final CountryMapper mapper;

    @Override
    public List<CountryDTO> list() {
        return  mapper.list((List<Country>)repository.findAll());
    }
}
