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
public class ResourceTradeTest extends SpringIntegrationTest implements Pt {

    private String payload;
    private ResultActions response;

    public ResourceTradeTest() {
        Dado(
                "^que desejo realizar uma negociação de recursos com as seguintes informações:$",
                (DataTable dataTable) -> payload = dataTableToPayload(dataTable));

        Quando(
                "^executar a negociação através do endpoint \"([^\"]*)\"$",
                (String endpoint) ->
                        response =
                                mvc.perform(
                                                post(endpoint)
                                                        .content(payload)
                                                        .contentType(MediaType.APPLICATION_JSON))
                                        .andDo(print()));

        Entao(
                "^a negociação deverá retornar o status \"([^\"]*)\"$",
                (String responseStatus) ->
                        response.andExpect(status().is(Integer.parseInt(responseStatus))));
    }

    private String dataTableToPayload(DataTable dataTable) throws JsonProcessingException {
        var row = dataTable.transpose().asList();
        var rootObject = mapper.createObjectNode();

        rootObject.put("idSoldierA", row.get(0)).put("idSoldierB", row.get(1));

        var resourcesA = rootObject.putObject("resourcesA");
        resourcesA.put("water", row.get(2));
        resourcesA.put("food", row.get(3));
        resourcesA.put("weapon", row.get(4));
        resourcesA.put("ammo", row.get(5));

        var resourcesB = rootObject.putObject("resourcesB");
        resourcesB.put("water", row.get(6));
        resourcesB.put("food", row.get(7));
        resourcesB.put("weapon", row.get(8));
        resourcesB.put("ammo", row.get(9));

        return mapper.writeValueAsString(rootObject);
    }
}
