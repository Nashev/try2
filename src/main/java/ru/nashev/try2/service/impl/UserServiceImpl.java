package ru.nashev.try2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nashev.try2.dao.UserRepository;
import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;
import ru.nashev.try2.mapper.UserMapper;
import ru.nashev.try2.model.User;
import ru.nashev.try2.service.UserService;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Реализация сервиса для работы с пользователями
 * @author Nashev
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserListDTO> list(UserFilter filter) {
        return mapper.list(
                repository.findAll(
                        (Specification<User>) (root, query, cb) -> {
                            List<Predicate> predicates = new ArrayList<>();
                            if (filter.getOfficeId() != null) {
                                predicates.add(cb.equal(root.get("office").get("id"), filter.getOfficeId()));
                            }
                            filter.getFirstName().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("firstName"), s), 0)));
                            filter.getSecondName().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("secondName"), s), 0)));
                            filter.getMiddleName().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("middleName"), s), 0)));
                            filter.getPosition().ifPresent(s -> predicates.add(cb.notEqual(cb.locate(root.get("position"), s), 0)));
                            filter.getDocCode().ifPresent(s -> predicates.add(cb.equal(root.get("userDoc").get("doc").get("code"), s)));
                            filter.getCitizenshipCode().ifPresent(s -> predicates.add(cb.equal(root.get("citizenship").get("code"), s)));
                            return cb.and(predicates.toArray(new Predicate[0]));
                        }
                ));
    }

    @Override
    public UserGetDTO get(Long id) {
        return mapper.get(repository.findById(id).orElseThrow(getNotFoundSupplier(id)));
    }

    @Override
    public void add(UserAddDTO dto) {
        repository.save(mapper.add(dto));
    }
    
    @Override
    @Transactional
    public void update(UserUpdateDTO input) {
        User user = repository.findById(input.getId()).orElseThrow(getNotFoundSupplier(input.getId()));
        mapper.update(user, input);
    }

    private Supplier<RecordNotFound> getNotFoundSupplier(Long id) {
        return () -> new RecordNotFound("User " + id + " does not exists.");
    }
}