package br.com.letscode.starwarsnetwork.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldierLocalizationUpdateResponse {

    private String latitude;
    private String longitude;
    private String baseName;
}
