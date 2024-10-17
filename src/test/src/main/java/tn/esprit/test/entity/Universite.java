package tn.esprit.test.entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class Universite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idUniversite;
    private String nomUniversite;
    private String adresse;

    @OneToOne
    private Foyer foyer;
}
