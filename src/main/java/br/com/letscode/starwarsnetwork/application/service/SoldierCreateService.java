package br.com.letscode.starwarsnetwork.application.service;

import br.com.letscode.starwarsnetwork.domain.model.dto.SoldierCreateRequest;
import br.com.letscode.starwarsnetwork.domain.model.entity.Soldier;
import br.com.letscode.starwarsnetwork.domain.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoldierCreateService {

    private final SoldierRepository soldierRepository;
    private final ModelMapper modelMapper;

    public Soldier createSoldier(SoldierCreateRequest soldierCreateRequest) {
        var soldier = modelMapper.map(soldierCreateRequest, Soldier.class);
        return soldierRepository.save(soldier);
    }
}
