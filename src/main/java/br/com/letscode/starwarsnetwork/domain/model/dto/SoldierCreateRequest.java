package br.com.letscode.starwarsnetwork.domain.model.dto;

import br.com.letscode.starwarsnetwork.domain.model.entity.Gender;
import br.com.letscode.starwarsnetwork.domain.model.entity.Inventory;
import br.com.letscode.starwarsnetwork.domain.model.entity.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SoldierCreateRequest {

    private String name;
    private Integer age;
    private Gender gender;
    private Localization localization;
    private Inventory inventory;
}
