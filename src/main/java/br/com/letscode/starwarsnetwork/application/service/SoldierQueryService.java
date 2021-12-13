package br.com.letscode.starwarsnetwork.application.service;

import br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesAverageResponse;
import br.com.letscode.starwarsnetwork.domain.model.entity.Soldier;
import br.com.letscode.starwarsnetwork.domain.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.AMMO;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.FOOD;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.WATER;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.WEAPON;

@Service
@RequiredArgsConstructor
public class SoldierQueryService {

    private final SoldierRepository soldierRepository;

    public Soldier getSoldierById(Long soldierID) {
        return soldierRepository
                .findById(soldierID)
                .orElseThrow(
                        () -> {
                            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
                        });
    }

    public double getPercentageOfTraitors() {
        var totalSoldiers = soldierRepository.count();
        var totalTraitors = soldierRepository.countByTraitorTrue();
        return Math.ceil((double) (totalTraitors * 100) / totalSoldiers);
    }

    public double getPercentageOfSoldiers() {
        return 100.0 - getPercentageOfTraitors();
    }

    public ResourcesAverageResponse getResourceAverageBySoldier() {
        return soldierRepository.findAverageByResources();
    }

    public long getLostPointsForTraitors() {
        var resources = soldierRepository.findSumResourcesByTraitors();
        var totalPointsLost = 0;
        totalPointsLost += WATER.getScoreByQuantity(resources.getSumWater());
        totalPointsLost += FOOD.getScoreByQuantity(resources.getSumFood());
        totalPointsLost += WEAPON.getScoreByQuantity(resources.getSumWeapon());
        totalPointsLost += AMMO.getScoreByQuantity(resources.getSumAmmo());

        return totalPointsLost;
    }
}
