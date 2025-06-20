package com.medo.deliverymatch.model;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expediteur  extends Utilisateur{
    private long telephone;

    private List<Demande> listeDemandes;

}
