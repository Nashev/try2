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
import ru.nashev.try2.dto.office.OfficeAddDTO;
import ru.nashev.try2.dto.office.OfficeFilter;
import ru.nashev.try2.dto.office.OfficeGetDTO;
import ru.nashev.try2.dto.office.OfficeListDTO;
import ru.nashev.try2.dto.office.OfficeUpdateDTO;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class OfficeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listFull() {
        ResponseEntity<OfficeListResponseDTO> response = restTemplate.postForEntity(
                "/api/office/list",
                new OfficeFilter(1L, Optional.empty(), Optional.empty(), Optional.empty()),
                OfficeListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val offices = response.getBody().getData();
        assertThat(offices).containsExactlyInAnyOrder(
                new OfficeListDTO(1L, "Головной оффис", true),
                new OfficeListDTO(2L, "Филлиал", true),
                new OfficeListDTO(3L, "Закрытый филлиал", false)
        );
    }

    @Test
    public void listByName() {
        ResponseEntity<OfficeListResponseDTO> response = restTemplate.postForEntity(
                "/api/office/list",
                new OfficeFilter(1L, Optional.of("лл"), Optional.empty(), Optional.empty()),
                OfficeListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val offices = response.getBody().getData();
        assertThat(offices).containsExactlyInAnyOrder(
                new OfficeListDTO(2L, "Филлиал", true),
                new OfficeListDTO(3L, "Закрытый филлиал", false)
        );
    }

    @Test
    public void listByPhone() {
        ResponseEntity<OfficeListResponseDTO> response = restTemplate.postForEntity(
                "/api/office/list",
                new OfficeFilter(1L, Optional.empty(), Optional.of("9"), Optional.empty()),
                OfficeListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val offices = response.getBody().getData();
        assertThat(offices).containsExactlyInAnyOrder(
                new OfficeListDTO(2L, "Филлиал", true)
        );
    }

    @Test
    public void listByActive() {
        ResponseEntity<OfficeListResponseDTO> response = restTemplate.postForEntity(
                "/api/office/list",
                new OfficeFilter(1L, Optional.empty(), Optional.empty(), Optional.of(false)),
                OfficeListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val offices = response.getBody().getData();
        assertThat(offices).containsExactlyInAnyOrder(
                new OfficeListDTO(3L, "Закрытый филлиал", false)
        );
    }

    @Test
    public void listByOrgIdNamePhoneAndActive() {
        ResponseEntity<OfficeListResponseDTO> response = restTemplate.postForEntity(
                "/api/office/list",
                new OfficeFilter(1L, Optional.of("л"), Optional.of("9"), Optional.of(true)),
                OfficeListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val offices = response.getBody().getData();
        assertThat(offices).containsExactlyInAnyOrder(
                new OfficeListDTO(2L, "Филлиал", true)
        );
    }

    @Test
    public void get() {
        ResponseEntity<OfficeGetResponseDTO> response = restTemplate.getForEntity(
                "/api/office/4",
                OfficeGetResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val office = response.getBody().getData();
        assertThat(office).isEqualTo(new OfficeGetDTO(
                4L,
                "Главкасса",
                "Омск, ул. Копателей, д.2",
                "+78003333333",
                true
        ));
    }

    @Test
    public void getNotExists() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.getForEntity(
                "/api/office/33",
                ResultErrorDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        val error = response.getBody().getError();
        assertThat(error).isEqualTo("Офис 33 не существует.");
    }

    @Test
    @DirtiesContext
    public void add() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/office/save",
                new OfficeAddDTO(
                        1L,
                        "AddedOffice",
                        "Argentina",
                        "567876545678",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<OfficeGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/office/6",
                OfficeGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val office = responseGet.getBody().getData();
        assertThat(office).isEqualTo(new OfficeGetDTO(
                6L,
                "AddedOffice",
                "Argentina",
                "567876545678",
                true
        ));
    }

    @Test
    @DirtiesContext
    public void update() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/office/update",
                new OfficeUpdateDTO(
                        1L,
                        "UpdatedOffice",
                        "Argentina",
                        "567876545678",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<OfficeGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/office/1",
                OfficeGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val office = responseGet.getBody().getData();
        assertThat(office).isEqualTo(new OfficeGetDTO(
                1L,
                "UpdatedOffice",
                "Argentina",
                "567876545678",
                true
        ));
    }

    @Test
    public void updateWithError() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.postForEntity(
                "/api/office/save",
                new OfficeUpdateDTO(
                        null,
                        "",
                        "",
                        "",
                        null
                ),
                ResultErrorDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        val error = response.getBody().getError();
        assertThat(error).isEqualTo("Ошибка в запросе, проблемы с полями:\n  orgId (не должно равняться null)");
    }

    @Data
    @NoArgsConstructor
    public static class OfficeListResponseDTO {
        List<OfficeListDTO> data;
    }

    @Data
    @NoArgsConstructor
    public static class OfficeGetResponseDTO {
        OfficeGetDTO data;
    }

    @Data
    @NoArgsConstructor
    public static class ResponseResultSuccessDTO {
        ResultSuccessDTO data;
    }
}