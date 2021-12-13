package br.com.letscode.starwarsnetwork.domain.model.dto;

import br.com.letscode.starwarsnetwork.domain.model.entity.Gender;
import br.com.letscode.starwarsnetwork.domain.model.entity.Localization;
import lombok.Data;

@Data
public class SoldierResponse {

    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private Localization localization;
}
