package com.medo.deliverymatch.dto;

public class DemandeDTO {
    private Long id;
    private String statut;
    private String trajet;
    private String date;
    private String expediteur;

    public DemandeDTO() {}

    public DemandeDTO(Long id, String statut, String trajet, String date, String expediteur) {
        this.id = id;
        this.statut = statut;
        this.trajet = trajet;
        this.date = date;
        this.expediteur = expediteur;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getTrajet() { return trajet; }
    public void setTrajet(String trajet) { this.trajet = trajet; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getExpediteur() { return expediteur; }
    public void setExpediteur(String expediteur) { this.expediteur = expediteur; }
} 