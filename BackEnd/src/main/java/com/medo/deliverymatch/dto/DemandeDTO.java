package com.medo.deliverymatch.dto;

public class DemandeDTO {
    private Long id;
    private String statut;
    private String trajet;
    private String date;

    public DemandeDTO() {}

    public DemandeDTO(Long id, String statut, String trajet, String date) {
        this.id = id;
        this.statut = statut;
        this.trajet = trajet;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getTrajet() { return trajet; }
    public void setTrajet(String trajet) { this.trajet = trajet; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
} 