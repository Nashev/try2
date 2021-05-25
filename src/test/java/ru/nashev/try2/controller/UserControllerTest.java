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
import ru.nashev.try2.dto.user.UserAddDTO;
import ru.nashev.try2.dto.user.UserFilter;
import ru.nashev.try2.dto.user.UserGetDTO;
import ru.nashev.try2.dto.user.UserListDTO;
import ru.nashev.try2.dto.user.UserUpdateDTO;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listFull() {
        ResponseEntity<UserListResponseDTO> response = restTemplate.postForEntity(
                "/api/user/list",
                new UserFilter(1L, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
                UserListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val users = response.getBody().getData();
        assertThat(users).containsExactlyInAnyOrder(
                new UserListDTO(1L, "Иванов", "Иван", "Иванович", "Владелец"),
                new UserListDTO(2L, "Петров", "Пётр", "Петрович", "Начальник")
        );
    }

    @Test
    public void listByName() {
        ResponseEntity<UserListResponseDTO> response = restTemplate.postForEntity(
                "/api/user/list",
                new UserFilter(1L, Optional.of("ан"), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()),
                UserListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val users = response.getBody().getData();
        assertThat(users).containsExactlyInAnyOrder(
                new UserListDTO(1L, "Иванов", "Иван", "Иванович", "Владелец")
        );
    }

    @Test
    public void listByCitizenship() {
        ResponseEntity<UserListResponseDTO> response = restTemplate.postForEntity(
                "/api/user/list",
                new UserFilter(4L, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("usa")),
                UserListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val users = response.getBody().getData();
        assertThat(users).containsExactlyInAnyOrder(
                new UserListDTO(4L, "Smith", "John", "Roland", "Субподрядчик")
        );
    }

    @Test
    public void listByPosition() {
        ResponseEntity<UserListResponseDTO> response = restTemplate.postForEntity(
                "/api/user/list",
                new UserFilter(1L, Optional.empty(), Optional.empty(), Optional.empty(), Optional.of("Владелец"), Optional.empty(), Optional.empty()),
                UserListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val users = response.getBody().getData();
        assertThat(users).containsExactlyInAnyOrder(
                new UserListDTO(1L, "Иванов", "Иван", "Иванович", "Владелец")
        );
    }

    @Test
    public void listByNamePositionAndDocCode() {
        ResponseEntity<UserListResponseDTO> response = restTemplate.postForEntity(
                "/api/user/list",
                new UserFilter(1L, Optional.of("Иванов"), Optional.empty(), Optional.empty(), Optional.of("делец"), Optional.of("21"), Optional.empty()),
                UserListResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val users = response.getBody().getData();
        assertThat(users).containsExactlyInAnyOrder(
                new UserListDTO(1L, "Иванов", "Иван", "Иванович", "Владелец")
        );
    }

    @Test
    public void get() {
        ResponseEntity<UserGetResponseDTO> response = restTemplate.getForEntity(
                "/api/user/4",
                UserGetResponseDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val user = response.getBody().getData();
        assertThat(user).isEqualTo(new UserGetDTO(
                4L,
                "Smith",
                "John",
                "Roland",
                "Субподрядчик",
                "+11234567890",
                "Паспорт иностранного гражданина",
                "987654",
                "11.09.10",
                "США",
                "usa",
                false
        ));
    }

    @Test
    public void getNotExists() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.getForEntity(
                "/api/user/33",
                ResultErrorDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        val error = response.getBody().getError();
        assertThat(error).isEqualTo("Пользователь 33 не существует.");
    }

    @Test
    @DirtiesContext
    public void add() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/user/save",
                new UserAddDTO(1L,
                        "Васильев",
                        "Василий",
                        "Васильевич",
                        "Мимокрокодил",
                        "+71234567890",
                        "15",
                        "Паспорт урки",
                        "1234 567890",
                        "01.01.19",
                        "ua",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<UserGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/user/5",
                UserGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val user = responseGet.getBody().getData();
        assertThat(user).isEqualTo(new UserGetDTO(
                5L,
                "Васильев",
                "Василий",
                "Васильевич",
                "Мимокрокодил",
                "+71234567890",
                "Паспорт урки",
                "1234 567890",
                "01.01.19",
                "Украина",
                "ua",
                true
        ));
    }

    @Test
    @DirtiesContext
    public void update() {
        ResponseEntity<ResponseResultSuccessDTO> response = restTemplate.postForEntity(
                "/api/user/update",
                new UserUpdateDTO(
                        4L,
                        2L,
                        "Васильева",
                        "Василиса",
                        "Васильевна",
                        "Мимокрокодилка",
                        "+70987654321",
                        "Паспорт гражданина Российской Федерации",
                        "1234 567890",
                        "01.01.19",
                        "ua",
                        true
                ),
                ResponseResultSuccessDTO.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData().getResult()).isEqualTo("success");

        // проверяем, что организация реально добавилась:
        ResponseEntity<UserGetResponseDTO> responseGet = restTemplate.getForEntity(
                "/api/user/4",
                UserGetResponseDTO.class
        );
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);
        val user = responseGet.getBody().getData();
        assertThat(user).isEqualTo(new UserGetDTO(
                4L,
                "Васильева",
                "Василиса",
                "Васильевна",
                "Мимокрокодилка",
                "+70987654321",
                "Паспорт гражданина Российской Федерации",
                "1234 567890",
                "01.01.19",
                "Украина",
                "ua",
                true
        ));
    }

    @Test
    public void updateWithError() {
        ResponseEntity<ResultErrorDTO> response = restTemplate.postForEntity(
                "/api/user/save",
                new UserUpdateDTO(
                        null,
                        null,
                        "",
                        "",
                        "",
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
        assertThat(error).isEqualTo("Ошибка в запросе, проблемы с полями:\n" +
                "  firstName (не должно быть пустым)\n" +
                "  officeId (не должно равняться null)\n" +
                "  position (не должно быть пустым)");
    }

    @Data
    @NoArgsConstructor
    public static class UserListResponseDTO {
        List<UserListDTO> data;
    }

    @Data
    @NoArgsConstructor
    public static class UserGetResponseDTO {
        UserGetDTO data;
    }

    @Data
    @NoArgsConstructor
    public static class ResponseResultSuccessDTO {
        ResultSuccessDTO data;
    }
}