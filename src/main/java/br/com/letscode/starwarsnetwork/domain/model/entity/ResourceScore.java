package br.com.letscode.starwarsnetwork.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResourceScore {
    WEAPON(4),
    AMMO(3),
    WATER(2),
    FOOD(1);

    private Integer score;

    public long getScoreByQuantity(long quantity) {
        return quantity * this.score;
    }
}
