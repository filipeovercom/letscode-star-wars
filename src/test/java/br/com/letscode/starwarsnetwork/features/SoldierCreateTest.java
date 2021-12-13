package br.com.letscode.starwarsnetwork.features;

import br.com.letscode.starwarsnetwork.infraestructure.SpringIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.Pt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class SoldierCreateTest extends SpringIntegrationTest implements Pt {

    private String payload;
    private ResultActions response;

    public SoldierCreateTest() {
        Dado(
                "^que desejo cadastrar um soldado rebelde com as seguintes informações:$",
                (DataTable data) -> payload = dataTableToCreateRequest(data));

        Quando(
                "^executar a ação POST para o endpoint \"([^\"]*)\"$",
                (String endpoint) -> {
                    var request =
                            post(endpoint).content(payload).contentType(MediaType.APPLICATION_JSON);
                    response = mvc.perform(request).andDo(print());
                });

        Entao(
                "^a criação deverá retornar o status \"([^\"]*)\"$",
                (String responseStatus) ->
                        response.andExpect(status().is(Integer.parseInt(responseStatus))));
    }

    private String dataTableToCreateRequest(DataTable data) throws JsonProcessingException {
        var row = data.transpose().asList();
        var requestContent = mapper.createObjectNode();

        requestContent.put("name", row.get(0));
        requestContent.put("age", row.get(1));
        requestContent.put("gender", row.get(2));

        var localization = requestContent.putObject("localization");
        localization
                .put("latitude", row.get(3))
                .put("longitude", row.get(4))
                .put("baseName", row.get(5));

        var inventory = requestContent.putObject("inventory");
        inventory
                .put("water", row.get(6))
                .put("food", row.get(7))
                .put("weapon", row.get(8))
                .put("ammo", row.get(9));

        return mapper.writeValueAsString(requestContent);
    }
}
