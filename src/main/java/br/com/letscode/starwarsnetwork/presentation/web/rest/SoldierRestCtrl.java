package br.com.letscode.starwarsnetwork.presentation.web.rest;

import br.com.letscode.starwarsnetwork.application.service.SoldierCreateService;
import br.com.letscode.starwarsnetwork.application.service.SoldierUpdateService;
import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierBetrayalResponse;
import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierCreateRequest;
import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierLocalizationUpdateRequest;
import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierLocalizationUpdateResponse;
import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/soldier")
@RequiredArgsConstructor
public class SoldierRestCtrl {

    private final SoldierCreateService createService;
    private final SoldierUpdateService updateService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SoldierResponse createSoldier(
            @RequestBody @Valid SoldierCreateRequest soldierCreateRequest) {
        return modelMapper.map(
                createService.createSoldier(soldierCreateRequest), SoldierResponse.class);
    }

    @PutMapping("/{id}/localization")
    @ResponseStatus(HttpStatus.OK)
    public SoldierLocalizationUpdateResponse updateSoldierLocalization(
            @PathVariable Long id,
            @RequestBody @Valid SoldierLocalizationUpdateRequest updateRequest) {
        var soldier = updateService.updateSoldierLocalization(id, updateRequest);
        return SoldierLocalizationUpdateResponse.builder()
                .latitude(soldier.getLocalization().getLatitude())
                .longitude(soldier.getLocalization().getLongitude())
                .baseName(soldier.getLocalization().getBaseName())
                .build();
    }

    @PutMapping("/{id}/traitor")
    @ResponseStatus(HttpStatus.OK)
    public SoldierBetrayalResponse updateSoldierTraitor(@PathVariable Long id) {
        var soldier = updateService.updateSoldierBetrayals(id);
        return SoldierBetrayalResponse.builder()
                .traitor(soldier.getTraitor())
                .totalBetrayalsReported(soldier.getBetrayals().size())
                .build();
    }
}
