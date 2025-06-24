package com.medo.deliverymatch.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "requetes_livraison")
public class RequeteLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trajet_id", nullable = false)
    private Trajet trajet;

    @ManyToOne
    @JoinColumn(name = "expediteur_id", nullable = false)
    private Utilisateur expediteur;

    private double poids;
    private double longueur;
    private double largeur;
    private double hauteur;

    @Enumerated(EnumType.STRING)
    private Trajet.TypeColis typeColis;

    @Enumerated(EnumType.STRING)
    private StatutRequete statut;

    private String description;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_mise_a_jour")
    private Timestamp dateMiseAJour;

    public enum StatutRequete {
        EN_ATTENTE, ACCEPTEE, REFUSEE, TERMINEE
    }

    public RequeteLivraison() {
    }

    public RequeteLivraison(Trajet trajet, Utilisateur expediteur, double poids, double longueur,
                            double largeur, double hauteur, Trajet.TypeColis typeColis, String description) {
        this.trajet = trajet;
        this.expediteur = expediteur;
        this.poids = poids;
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.typeColis = typeColis;
        this.description = description;
        this.statut = StatutRequete.EN_ATTENTE;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
        this.dateMiseAJour = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Utilisateur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Utilisateur expediteur) {
        this.expediteur = expediteur;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public Trajet.TypeColis getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(Trajet.TypeColis typeColis) {
        this.typeColis = typeColis;
    }

    public StatutRequete getStatut() {
        return statut;
    }

    public void setStatut(StatutRequete statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void accepter() {
        this.statut = StatutRequete.ACCEPTEE;
        updateLastModification();
    }

    public void refuser() {
        this.statut = StatutRequete.REFUSEE;
        updateLastModification();
    }

    public void marquerCommeTerminee() {
        this.statut = StatutRequete.TERMINEE;
        updateLastModification();
    }
}
