package com.medo.deliverymatch.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trajets")
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lieuDepart;

    private String etapesIntermediaires;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Timestamp dateDepart;

    private double poidsMax;

    private double longueurMax;
    private double largeurMax;
    private double hauteurMax;

    @Enumerated(EnumType.STRING)
    private TypeColis typeColis;

    private boolean actif;

    @ManyToOne
    @JoinColumn(name = "conducteur_id", nullable = false)
    private Utilisateur conducteur;

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    private List<RequeteLivraison> requetesLivraison;

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    private List<Demande> demandes;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_mise_a_jour")
    private Timestamp dateMiseAJour;

    public enum TypeColis {
        DOCUMENTS, PETIT_COLIS, MEDIUM_COLIS, GRAND_COLIS, FRAGILE
    }

    public Trajet() {
    }

    public Trajet(String lieuDepart, String destination, Timestamp dateDepart,
                 double poidsMax, double longueurMax, double largeurMax, double hauteurMax,
                 TypeColis typeColis, Utilisateur conducteur) {
        this.lieuDepart = lieuDepart;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.poidsMax = poidsMax;
        this.longueurMax = longueurMax;
        this.largeurMax = largeurMax;
        this.hauteurMax = hauteurMax;
        this.typeColis = typeColis;
        this.conducteur = conducteur;
        this.actif = true;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
        this.dateMiseAJour = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getEtapesIntermediaires() {
        return etapesIntermediaires;
    }

    public void setEtapesIntermediaires(String etapesIntermediaires) {
        this.etapesIntermediaires = etapesIntermediaires;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Timestamp dateDepart) {
        this.dateDepart = dateDepart;
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(double poidsMax) {
        this.poidsMax = poidsMax;
    }

    public double getLongueurMax() {
        return longueurMax;
    }

    public void setLongueurMax(double longueurMax) {
        this.longueurMax = longueurMax;
    }

    public double getLargeurMax() {
        return largeurMax;
    }

    public void setLargeurMax(double largeurMax) {
        this.largeurMax = largeurMax;
    }

    public double getHauteurMax() {
        return hauteurMax;
    }

    public void setHauteurMax(double hauteurMax) {
        this.hauteurMax = hauteurMax;
    }

    public TypeColis getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(TypeColis typeColis) {
        this.typeColis = typeColis;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Utilisateur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Utilisateur conducteur) {
        this.conducteur = conducteur;
    }

    public List<RequeteLivraison> getRequetesLivraison() {
        return requetesLivraison;
    }

    public void setRequetesLivraison(List<RequeteLivraison> requetesLivraison) {
        this.requetesLivraison = requetesLivraison;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Timestamp dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public void updateLastModification() {
        this.dateMiseAJour = new Timestamp(System.currentTimeMillis());
    }

    public double getVolumeMax() {
        return longueurMax * largeurMax * hauteurMax;
    }
}