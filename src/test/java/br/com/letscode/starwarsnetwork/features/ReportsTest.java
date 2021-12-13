package br.com.letscode.starwarsnetwork.features;

import br.com.letscode.starwarsnetwork.application.service.SoldierQueryService;
import br.com.letscode.starwarsnetwork.application.service.SoldierUpdateService;
import br.com.letscode.starwarsnetwork.domain.model.entity.Inventory;
import br.com.letscode.starwarsnetwork.infraestructure.SpringIntegrationTest;
import io.cucumber.java8.Pt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ReportsTest extends SpringIntegrationTest implements Pt {

    private ResultActions response;

    @Autowired private SoldierUpdateService soldierUpdateService;
    @Autowired private SoldierQueryService soldierQueryService;

    public ReportsTest() {
        Dado(
                "^que desejo saber o percentual de traidores$",
                () -> log.info("Buscando percentual de traidores"));

        Dado(
                "^que desejo saber o percentual de soldados$",
                () -> log.info("Buscando percentual de soldados"));

        Dado(
                "^que desejo saber o total de pontos perdidos a traidores$",
                () -> log.info("Buscando o total de pontos perdidos a traidores"));

        Dado(
                "^que desejo saber a média de recursos por soldados não traidores$",
                () -> {
                    normalizeInventoryForTests();
                    log.info("Buscando a média de recursos por soldado");
                });

        Quando(
                "^executar a chamada ao endpoint \"([^\"]*)\"$",
                (String endpoint) -> response = mvc.perform(get(endpoint)).andDo(print()));

        Entao(
                "^a chamada deverá retornar o status \"([^\"]*)\"$",
                (String responseStatus) -> response.andExpect(status().isOk()));

        E(
                "^o percentual de traidores deve ser de \"([^\"]*)\"%$",
                (String expectedPercentage) ->
                        response.andExpect(content().string(expectedPercentage)));

        E(
                "^o total de pontos perdidos a traidores deve ser de \"([^\"]*)\"$",
                (String expectedTotalPoints) ->
                        response.andExpect(content().string(expectedTotalPoints)));

        E(
                "^a média de todos os itens por soldado deve ser de \"([^\"]*)\"$",
                (String expectedAverage) -> {
                    var valueExpected = Double.parseDouble(expectedAverage);
                    response.andExpect(jsonPath("$.avgWater", equalTo(valueExpected)))
                            .andExpect(jsonPath("$.avgFood", equalTo(valueExpected)))
                            .andExpect(jsonPath("$.avgWeapon", equalTo(valueExpected)))
                            .andExpect(jsonPath("$.avgAmmo", equalTo(valueExpected)));
                });
    }

    public void normalizeInventoryForTests() {
        log.info("Building test state");
        var invetory = Inventory.builder().water(100).food(100).weapon(100).ammo(100).build();
        var soldier1 = soldierQueryService.getSoldierById(1L);
        var soldier2 = soldierQueryService.getSoldierById(2L);
        soldier1.setInventory(invetory);
        soldier2.setInventory(invetory);
        soldierUpdateService.update(soldier1);
        soldierUpdateService.update(soldier2);
    }
}
