package com.medo.deliverymatch.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double poids;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private String typeColis;

    @Column(nullable = false)
    private String dimensions;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utilisateur expediteur;

    @ManyToOne
    private Trajet trajet;

    @OneToOne(mappedBy = "colis", cascade = CascadeType.ALL)
    @JoinColumn(name = "demande_id")
    private Demande demande;

    public Colis() {
    }

    public Colis(Long id, String description, double poids, double volume, String typeColis, String dimensions, LocalDate dateCreation, Utilisateur expediteur, Trajet trajet, Demande demande) {
        this.id = id;
        this.description = description;
        this.poids = poids;
        this.volume = volume;
        this.typeColis = typeColis;
        this.dimensions = dimensions;
        this.dateCreation = dateCreation;
        this.expediteur = expediteur;
        this.trajet = trajet;
        this.demande = demande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(String typeColis) {
        this.typeColis = typeColis;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
}

