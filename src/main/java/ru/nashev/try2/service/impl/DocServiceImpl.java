package ru.nashev.try2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nashev.try2.dao.DocRepository;
import ru.nashev.try2.dto.DocDTO;
import ru.nashev.try2.mapper.DocMapper;
import ru.nashev.try2.model.Doc;
import ru.nashev.try2.service.DocService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DocServiceImpl implements DocService {
    private final DocRepository repository;
    private final DocMapper mapper;

    @Override
    public List<DocDTO> list() {
        return mapper.list((List<Doc>) repository.findAll());
    }
}
