package br.com.letscode.starwarsnetwork.domain.model.dto;

import br.com.letscode.starwarsnetwork.domain.model.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeResourcesResponse {

    private Inventory newInventorySoldierA;
    private Inventory newInventorySoldierB;
}
