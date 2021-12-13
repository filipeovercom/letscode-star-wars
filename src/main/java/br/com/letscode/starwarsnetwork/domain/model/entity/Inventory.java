package br.com.letscode.starwarsnetwork.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.AMMO;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.FOOD;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.WATER;
import static br.com.letscode.starwarsnetwork.domain.model.entity.ResourceScore.WEAPON;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Inventory {

    @NotNull @PositiveOrZero @Builder.Default private Integer water = 0;
    @NotNull @PositiveOrZero @Builder.Default private Integer food = 0;
    @NotNull @PositiveOrZero @Builder.Default private Integer weapon = 0;
    @NotNull @PositiveOrZero @Builder.Default private Integer ammo = 0;

    public Long totalScore() {
        var totalScore = 0L;
        totalScore += (long) WATER.getScore() * getWater();
        totalScore += (long) FOOD.getScore() * getFood();
        totalScore += (long) WEAPON.getScore() * getWeapon();
        totalScore += (long) AMMO.getScore() * getAmmo();
        return totalScore;
    }

    public boolean hasAvailabilityToTrade(Inventory outgoing) {
        return (getWater() >= outgoing.getWater())
                && (getFood() >= outgoing.getFood())
                && (getWeapon() >= outgoing.getWeapon())
                && (getAmmo() >= outgoing.getAmmo());
    }

    public void tradeBy(Inventory outgoing, Inventory incoming) {
        setWater((getWater() - outgoing.getWater()) + incoming.getWater());
        setFood((getFood() - outgoing.getFood()) + incoming.getFood());
        setAmmo((getAmmo() - outgoing.getAmmo()) + incoming.getAmmo());
        setWeapon((getWeapon() - outgoing.getWeapon()) + incoming.getWeapon());
    }
}
