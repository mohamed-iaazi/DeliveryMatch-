package com.medo.deliverymatch.model;


import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Expediteur  extends Utilisateur{
    private long telephone;
}
