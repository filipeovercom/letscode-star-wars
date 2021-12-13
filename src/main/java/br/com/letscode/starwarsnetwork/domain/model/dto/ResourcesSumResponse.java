package br.com.letscode.starwarsnetwork.domain.model.dto;

import lombok.Value;

@Value
public class ResourcesSumResponse {

    long sumWater;
    long sumFood;
    long sumWeapon;
    long sumAmmo;
}
