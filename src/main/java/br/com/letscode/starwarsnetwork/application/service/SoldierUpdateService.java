package br.com.letscode.starwarsnetwork.application.service;

import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierLocalizationUpdateRequest;
import br.com.letscode.starwarsnetwork.domain.model.entity.Localization;
import br.com.letscode.starwarsnetwork.domain.model.entity.Soldier;
import br.com.letscode.starwarsnetwork.domain.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoldierUpdateService {
    public static final int MAX_BETRAYAL_REPORTS = 3;

    private final SoldierQueryService soldierQueryService;
    private final SoldierRepository soldierRepository;
    private final ModelMapper modelMapper;

    public Soldier updateSoldierLocalization(
            Long soldierID, SoldierLocalizationUpdateRequest request) {
        var soldier = soldierQueryService.getSoldierById(soldierID);

        var newLocalization = modelMapper.map(request, Localization.class);
        soldier.setLocalization(newLocalization);
        return soldierRepository.save(soldier);
    }

    public Soldier updateSoldierBetrayals(Long soldierID) {
        var soldier = soldierQueryService.getSoldierById(soldierID);

        soldier.reportBetrayal();

        var totalBetrayalsReported = soldier.getBetrayals().size();

        if (totalBetrayalsReported >= MAX_BETRAYAL_REPORTS) {
            soldier.setTraitor(true);
        }

        return soldierRepository.save(soldier);
    }

    public Soldier update(Soldier soldier) {
        return soldierRepository.save(soldier);
    }
}
