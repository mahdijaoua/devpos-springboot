package tn.esprit.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFoyer;
    private String nomFoyer;
    private long capaciteFoyer;
    @OneToOne(mappedBy="foyer")
    @JsonIgnore
    private Universite universite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="foyer")
    @JsonIgnore
    private Set<Bloc> blocs;
}