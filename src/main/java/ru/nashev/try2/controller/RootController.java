package ru.nashev.try2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Отвечает за то, что отображается в корне сервиса.
 * @author Nashev
 */
@RestController
public class RootController {
    @GetMapping(path = "/")
    public String RootPage(){
        return "<ul><li><a href='/swagger-ui/'>Swagger</a></li> <li><a href='/h2-console/'>H2 console</a></li></ul>";
    }
}
