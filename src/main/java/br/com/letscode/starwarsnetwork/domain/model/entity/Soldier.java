package br.com.letscode.starwarsnetwork.domain.model.entity;

import br.com.letscode.starwarsnetwork.infrastructure.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Soldier implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String name;

    @NotNull @Positive private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded @Valid private Localization localization;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "soldier")
    @Cascade(CascadeType.ALL)
    private List<Betrayal> betrayals;

    @NotNull @Builder.Default private Boolean traitor = Boolean.FALSE;

    @Embedded @Valid private Inventory inventory;

    public void reportBetrayal() {
        getBetrayals().add(Betrayal.builder().soldier(this).build());
    }

    public List<Betrayal> getBetrayals() {
        return ofNullable(betrayals).orElse(new ArrayList<>());
    }
}
