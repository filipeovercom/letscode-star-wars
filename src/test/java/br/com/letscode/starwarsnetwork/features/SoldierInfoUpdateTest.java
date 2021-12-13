package br.com.letscode.starwarsnetwork.features;

import br.com.letscode.starwarsnetwork.infraestructure.SpringIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.Pt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class SoldierInfoUpdateTest extends SpringIntegrationTest implements Pt {

    private String payload;
    private ResultActions response;

    public SoldierInfoUpdateTest() {
        Dado(
                "^que desejo atualizar a localização de um soldado rebelde com as seguintes informações:$",
                (DataTable data) -> payload = dataTableToLocalizationUpdateRequest(data));

        Quando(
                "^executar a atualização da localização no endpoint \"([^\"]*)\"$",
                (String endpoint) -> {
                    var request =
                            put(endpoint).content(payload).contentType(MediaType.APPLICATION_JSON);
                    response = mvc.perform(request).andDo(print());
                });

        Entao(
                "^a atualização deverá retornar o status \"([^\"]*)\"$",
                (String responseStatus) -> {
                    var responses = responseStatus.split("_");
                    if (responses.length > 1) {
                        response.andExpect(
                                jsonPath("$.traitor", equalTo(Boolean.valueOf(responses[1]))));
                    } else {
                        response.andExpect(status().is(Integer.parseInt(responseStatus)));
                    }
                });

        Dado(
                "^que desejo reportar um soldado como traidor$",
                () -> log.info("Reportando soldado como traidor"));

        Quando(
                "^reportar a traição no endpoint \"([^\"]*)\"$",
                (String endpoint) -> {
                    var request = put(endpoint).contentType(MediaType.APPLICATION_JSON);
                    response = mvc.perform(request).andDo(print());
                });
    }

    private String dataTableToLocalizationUpdateRequest(DataTable data)
            throws JsonProcessingException {
        var row = data.transpose().asList();

        return mapper.writeValueAsString(
                mapper.createObjectNode()
                        .put("latitude", row.get(0))
                        .put("longitude", row.get(1))
                        .put("baseName", row.get(2)));
    }
}
