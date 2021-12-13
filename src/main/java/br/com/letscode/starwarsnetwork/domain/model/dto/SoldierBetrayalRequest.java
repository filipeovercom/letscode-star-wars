package br.com.letscode.starwarsnetwork.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldierBetrayalRequest {

    private Boolean traitor;
}
