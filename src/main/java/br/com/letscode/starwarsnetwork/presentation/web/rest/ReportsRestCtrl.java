package br.com.letscode.starwarsnetwork.presentation.web.rest;

import br.com.letscode.starwarsnetwork.application.service.SoldierQueryService;
import br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesAverageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportsRestCtrl {

    private final SoldierQueryService queryService;

    @GetMapping("/traitors/percentage")
    public double getPercentageOfTraitors() {
        return queryService.getPercentageOfTraitors();
    }

    @GetMapping("/soldiers/percentage")
    public double getPercentageOfSoldiers() {
        return queryService.getPercentageOfSoldiers();
    }

    @GetMapping("/resources/average")
    public ResourcesAverageResponse getAverageOfResources() {
        return queryService.getResourceAverageBySoldier();
    }

    @GetMapping("/traitors/lostpoints")
    public long getLostPointsForTraitors() {
        return queryService.getLostPointsForTraitors();
    }
}
