package ru.nashev.try2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nashev.try2.dao.OfficeRepository;
import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeFilter;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;
import ru.nashev.try2.mapper.OfficeMapper;
import ru.nashev.try2.model.Office;
import ru.nashev.try2.service.OfficeService;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Реализация сервиса для работы с офисами
 * @author Nashev
 */
@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository repository;
    private final OfficeMapper mapper;

    @Override
    public List<OfficeListDTO> list(OfficeFilter filter) {
        return mapper.list (
                repository.findAll(
                        (Specification<Office>) (root, query, cb) -> {
                            List<Predicate> predicates = new ArrayList<>();
                            predicates.add(cb.equal(root.get("organization").get("id"), filter.getOrgId()));
                            filter.getName().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("name"), s), 0)));
                            filter.getPhone().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("phone"), s), 0)));
                            filter.getIsActive().ifPresent(b -> predicates.add(cb.equal(root.get("isActive"), b)));
                            return cb.and(predicates.toArray(new Predicate[0]));
                        }
                ));
    }

    @Override
    public OfficeGetDTO get(Long id) {
        return mapper.get(repository.findById(id).orElseThrow(getNotFoundSupplier(id)));
    }

    @Override
    public void add(OfficeAddDTO input) {
        repository.save(mapper.add(input));
    }

    @Override
    @Transactional
    public void update(OfficeUpdateDTO input) {
        Office organization = repository.findById(input.getId()).orElseThrow(getNotFoundSupplier(input.getId()));
        mapper.update(organization, input);
    }

    private Supplier<RecordNotFound> getNotFoundSupplier(Long id) {
        return () -> new RecordNotFound("Офис " + id + " не существует.");
    }
}