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
import ru.nashev.try2.dto.DocDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@NoArgsConstructor
public class DocControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void list() {
        ResponseEntity<DocsResponseDTO> response = restTemplate.postForEntity("/api/docs", null, DocsResponseDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        val docs = response.getBody().getData();
        assertThat(docs).containsExactlyInAnyOrder(
                new DocDTO("03", "Свидетельство о рождении"),
                new DocDTO("07", "Военный билет"),
                new DocDTO("08", "Временное удостоверение, выданное взамен военного билета"),
                new DocDTO("10", "Паспорт иностранного гражданина"),
                new DocDTO("11", "Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу"),
                new DocDTO("12", "Вид на жительство в Российской Федерации"),
                new DocDTO("13", "Удостоверение беженца"),
                new DocDTO("15", "Разрешение на временное проживание в Российской Федерации"),
                new DocDTO("18", "Свидетельство о предоставлении временного убежища на территории Российской Федерации"),
                new DocDTO("21", "Паспорт гражданина Российской Федерации"),
                new DocDTO("23", "Свидетельство о рождении, выданное уполномоченным органом иностранного государства"),
                new DocDTO("24", "Удостоверение личности военнослужащего Российской Федерации"),
                new DocDTO("91", "Иные документы")
        );
    }

    @Data
    @NoArgsConstructor
    private static class DocsResponseDTO {
        List<DocDTO> data;
    }
}