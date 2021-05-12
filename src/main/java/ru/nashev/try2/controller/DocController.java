package ru.nashev.try2.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nashev.try2.dto.DocDTO;
import ru.nashev.try2.service.DocService;

import java.util.List;

@Api(tags = "Dictionaries")
@RequestMapping("/api/docs")
@RestController
@RequiredArgsConstructor
public class DocController {
    private final DocService docService;

    @ApiOperation("Get list of known doc types")
    @PostMapping
    public List<DocDTO> list() {
        return docService.list();
    }
}
