package ru.nashev.try2.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nashev.try2.dto.CountryDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class CountryControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void list() {
        ResponseEntity<CountriesResponseDTO> response = restTemplate.postForEntity("/api/countries", null, CountriesResponseDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val countries = response.getBody().getData();
        assertThat(countries).containsExactlyInAnyOrder(
                new CountryDTO("ru", "Россия"),
                new CountryDTO("ua", "Украина"),
                new CountryDTO("de", "Германия"),
                new CountryDTO("usa", "США"),
                new CountryDTO("gd", "Гондурас")
        );
    }

    @Data
    @NoArgsConstructor
    public static class CountriesResponseDTO {
        List<CountryDTO> data;
    }
}