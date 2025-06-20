package com.medo.deliverymatch.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDepart;
    @Column(nullable = false)
    private String lieuDepart;
    @Column(nullable = false)
    private String destination;

    private String dimensionsMax;
    private String typeMarchandise;
    private int capaciteDisponible;
    private List<String> listeDemandes;


    @ManyToOne
    @JoinColumn( nullable = false)
    private Utilisateur conducteur;

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    private List<Demande> demandesRecues;
}