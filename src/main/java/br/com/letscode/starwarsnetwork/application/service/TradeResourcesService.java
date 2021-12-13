package br.com.letscode.starwarsnetwork.application.service;

import br.com.letscode.starwarsnetwork.domain.model.dto.TradeResourcesRequest;
import br.com.letscode.starwarsnetwork.domain.model.dto.TradeResourcesResponse;
import br.com.letscode.starwarsnetwork.domain.model.entity.Inventory;
import br.com.letscode.starwarsnetwork.domain.model.entity.Soldier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class TradeResourcesService {

    private final SoldierQueryService soldierQueryService;
    private final SoldierUpdateService soldierUpdateService;

    public TradeResourcesResponse tradeResources(TradeResourcesRequest request) {
        var soldierA = soldierQueryService.getSoldierById(request.getIdSoldierA());
        var soldierB = soldierQueryService.getSoldierById(request.getIdSoldierB());

        validateTrade(soldierA, soldierB, request);
        soldierA.getInventory().tradeBy(request.getResourcesA(), request.getResourcesB());
        soldierB.getInventory().tradeBy(request.getResourcesB(), request.getResourcesA());

        var updatedSoldierA = soldierUpdateService.update(soldierA);
        var updatedSoldierB = soldierUpdateService.update(soldierB);

        return TradeResourcesResponse.builder()
                .newInventorySoldierA(updatedSoldierA.getInventory())
                .newInventorySoldierB(updatedSoldierB.getInventory())
                .build();
    }

    private void validateTrade(Soldier soldierA, Soldier soldierB, TradeResourcesRequest request) {
        validateSameScore(request);
        validateTraitor(soldierA);
        validateTraitor(soldierB);
        valideAvailabilityToTrade(soldierA, request.getResourcesA());
        valideAvailabilityToTrade(soldierB, request.getResourcesB());
    }

    private void validateSameScore(TradeResourcesRequest request) {
        var isValid = request.hasSameScore();
        if (!isValid) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST, "All traders must have the same resources score!");
        }
    }

    private void validateTraitor(Soldier soldier) {
        var message = "Soldier %s is a traitor and cannot trade resources!";
        var isTraitor = soldier.getTraitor();
        if (isTraitor) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST, String.format(message, soldier.getName()));
        }
    }

    private void valideAvailabilityToTrade(Soldier soldier, Inventory outcoming) {
        var message = "Soldier %s don't have necessary resources to trade!";
        var hasAvailability = soldier.getInventory().hasAvailabilityToTrade(outcoming);
        if (!hasAvailability) {
            throw new HttpClientErrorException(
                    HttpStatus.BAD_REQUEST, String.format(message, soldier.getName()));
        }
    }
}
