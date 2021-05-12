package ru.nashev.try2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nashev.try2.dao.OrganizationRepository;
import ru.nashev.try2.dto.organization.OrganizationAddDTO;
import ru.nashev.try2.dto.organization.OrganizationFilter;
import ru.nashev.try2.dto.organization.OrganizationGetDTO;
import ru.nashev.try2.dto.organization.OrganizationListDTO;
import ru.nashev.try2.dto.organization.OrganizationUpdateDTO;
import ru.nashev.try2.mapper.OrganizationMapper;
import ru.nashev.try2.model.Organization;
import ru.nashev.try2.service.OrganizationService;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;

    @Override
    public List<OrganizationListDTO> list(OrganizationFilter filter) {
        return mapper.list (
                repository.findAll(
                        (Specification<Organization>) (root, query, cb) -> {
                            List<Predicate> predicates = new ArrayList<>();
                            predicates.add(cb.notEqual(cb.locate(root.get("name"), filter.getName()), 0));
                            filter.getInn().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("inn"), s), 0)));
                            filter.getIsActive().ifPresent(b -> predicates.add(cb.equal(root.get("isActive"), b)));
                            return cb.and(predicates.toArray(new Predicate[0]));
                        }
                ));
    }

    @Override
    public OrganizationGetDTO get(Long id) {
        return mapper.get(repository.findById(id).orElseThrow(getNotFoundSupplier(id)));
    }

    @Override
    public void add(OrganizationAddDTO input) {
        repository.save(mapper.add(input));
    }

    @Override
    @Transactional
    public void update(OrganizationUpdateDTO input) {
        Organization organization = repository.findById(input.getId()).orElseThrow(getNotFoundSupplier(input.getId()));
        mapper.update(organization, input);
    }

    private Supplier<RecordNotFound> getNotFoundSupplier(Long id) {
        return () -> new RecordNotFound("Organization " + id + " does not exists.");
    }
}