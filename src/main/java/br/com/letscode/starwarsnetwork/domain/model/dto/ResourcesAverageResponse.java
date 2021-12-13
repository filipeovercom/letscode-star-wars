package br.com.letscode.starwarsnetwork.domain.model.dto;

import lombok.Value;

@Value
public class ResourcesAverageResponse {

    Double avgWater;
    Double avgFood;
    Double avgWeapon;
    Double avgAmmo;
}
