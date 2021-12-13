package br.com.letscode.starwarsnetwork.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Localization implements Serializable {

    @NotBlank private String latitude;
    @NotBlank private String longitude;
    @NotBlank private String baseName;
}
