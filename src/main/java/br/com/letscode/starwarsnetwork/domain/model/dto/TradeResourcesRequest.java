package br.com.letscode.starwarsnetwork.domain.model.dto;

import br.com.letscode.starwarsnetwork.domain.model.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeResourcesRequest {

    @NotNull private Long idSoldierA;
    @NotNull private Long idSoldierB;
    @NotNull @Valid private Inventory resourcesA;
    @NotNull @Valid private Inventory resourcesB;

    public boolean hasSameScore() {
        return Objects.equals(resourcesA.totalScore(), resourcesB.totalScore());
    }
}
