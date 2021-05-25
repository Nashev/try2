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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nashev.try2.dto.ResultErrorDTO;
import ru.nashev.try2.dto.ResultSuccessDTO;
import ru.nashev.try2.dto.organization.OrganizationAddDTO;
import ru.nashev.try2.dto.organization.OrganizationFilter;
import ru.nashev.try2.dto.organization.OrganizationGetDTO;
import ru.nashev.try2.dto.organization.OrganizationListDTO;
import ru.nashev.try2.dto.organization.OrganizationUpdateDTO;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class OrganizationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listFull() {
        ResponseEntity<OrganizationListResponseDTO> response = restTemplate.postForEntity(
                "/api/organization/list",
                new OrganizationFilter("", Optional.empty(), Optional.empty()),
                OrganizationListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organizations = response.getBody().getData();
        assertThat(organizations).containsExactlyInAnyOrder(
                new OrganizationListDTO(1L, "Рога и Копыта", true),
                new OrganizationListDTO(2L, "MMM", true),
                new OrganizationListDTO(3L, "Microsoft", false)
        );
    }

    @Test
    public void listByName() {
        ResponseEntity<OrganizationListResponseDTO> response = restTemplate.postForEntity(
                "/api/organization/list",
                new OrganizationFilter("M", Optional.empty(), Optional.empty()),
                OrganizationListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organizations = response.getBody().getData();
        assertThat(organizations).containsExactlyInAnyOrder(
                new OrganizationListDTO(2L, "MMM", true),
                new OrganizationListDTO(3L, "Microsoft", false)
        );
    }

    @Test
    public void listByInn() {
        ResponseEntity<OrganizationListResponseDTO> response = restTemplate.postForEntity(
                "/api/organization/list",
                new OrganizationFilter("", Optional.of("3333333"), Optional.empty()),
                OrganizationListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organizations = response.getBody().getData();
        assertThat(organizations).containsExactlyInAnyOrder(
                new OrganizationListDTO(3L, "Microsoft", false)
        );
    }

    @Test
    public void listByActive() {
        ResponseEntity<OrganizationListResponseDTO> response = restTemplate.postForEntity(
                "/api/organization/list",
                new OrganizationFilter("", Optional.empty(), Optional.of(false)),
                OrganizationListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organizations = response.getBody().getData();
        assertThat(organizations).containsExactlyInAnyOrder(
                new OrganizationListDTO(3L, "Microsoft", false)
        );
    }

    @Test
    public void listByNameInnAndActive() {
        ResponseEntity<OrganizationListResponseDTO> response = restTemplate.postForEntity(
                "/api/organization/list",
                new OrganizationFilter("M", Optional.of("1"), Optional.of(true)),
                OrganizationListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organizations = response.getBody().getData();
        assertThat(organizations).containsExactlyInAnyOrder(
                new OrganizationListDTO(2L, "MMM", true)
        );
    }

    @Test
    public void get() {
        ResponseEntity<OrganizationGetResponseDTO> response = restTemplate.getForEntity(
                "/api/organization/3",
                OrganizationGetResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organization = response.getBody().getData();
        assertThat(organization).isEqualTo(new OrganizationGetDTO(
                3L,
                "Microsoft",
                "Microsoft Ltd",
                "3333333",
                "3333",
                "New York",
                "1111111111",
                false
        ));
    }

    @Test
    public void getNotExists() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.getForEntity(
                "/api/organization/33",
                ResultErrorDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        val error = response.getBody().getError();
        assertThat(error).isEqualTo("Организация 33 не существует.");
    }

    @Test
    @DirtiesContext
    public void add() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/organization/save",
                new OrganizationAddDTO(
                        "AddedOrg",
                        "AddedOrg Ltd",
                        "4444444",
                        "4444",
                        "Argentina",
                        "567876545678",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<OrganizationGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/organization/4",
                OrganizationGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organization = responseGet.getBody().getData();
        assertThat(organization).isEqualTo(new OrganizationGetDTO(
                4L,
                "AddedOrg",
                "AddedOrg Ltd",
                "4444444",
                "4444",
                "Argentina",
                "567876545678",
                true
        ));
    }

    @Test
    @DirtiesContext
    public void update() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/organization/update",
                new OrganizationUpdateDTO(
                        1L,
                        "UpdatedOrg",
                        "UpdatedOrg Ltd",
                        "4444444",
                        "4444",
                        "Argentina",
                        "567876545678",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<OrganizationGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/organization/1",
                OrganizationGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val organization = responseGet.getBody().getData();
        assertThat(organization).isEqualTo(new OrganizationGetDTO(
                1L,
                "UpdatedOrg",
                "UpdatedOrg Ltd",
                "4444444",
                "4444",
                "Argentina",
                "567876545678",
                true
        ));
    }

    @Test
    public void updateWithError() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.postForEntity(
                "/api/organization/save",
                new OrganizationUpdateDTO(
                        null,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        null
                ),
                ResultErrorDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        val error = response.getBody().getError();
        assertThat(error).isEqualTo("Ошибка в запросе, проблемы с полями:\n  address (не должно быть пустым)\n  fullName (не должно быть пустым)\n  inn (не должно быть пустым)\n  kpp (не должно быть пустым)\n  name (не должно быть пустым)");
    }

    @Data
    @NoArgsConstructor
    public static class OrganizationListResponseDTO {
        List<OrganizationListDTO> data;
    }

    @Data
    @NoArgsConstructor
    public static class OrganizationGetResponseDTO {
        OrganizationGetDTO data;
    }

    @Data
    @NoArgsConstructor
    public static class ResponseResultSuccessDTO {
        ResultSuccessDTO data;
    }
}