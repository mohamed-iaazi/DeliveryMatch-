package com.medo.deliverymatch.dto;

import java.time.LocalDate;
import java.util.List;

public class TrajetDTO {
    private Long id;
    private String lieuDepart;
    private String destination;
    private List<String> etapesIntermediaires;
    private LocalDate dateDepart;
    private double poidsMax;
    private double volumeMax;
    private String typeMarchandise;
    private int capaciteDisponible;

    public TrajetDTO() {
    }

    public TrajetDTO(Long id, String lieuDepart, String destination, List<String> etapesIntermediaires, LocalDate dateDepart, double poidsMax, double volumeMax, String typeMarchandise, int capaciteDisponible) {
        this.id = id;
        this.lieuDepart = lieuDepart;
        this.destination = destination;
        this.etapesIntermediaires = etapesIntermediaires;
        this.dateDepart = dateDepart;
        this.poidsMax = poidsMax;
        this.volumeMax = volumeMax;
        this.typeMarchandise = typeMarchandise;
        this.capaciteDisponible = capaciteDisponible;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getEtapesIntermediaires() {
        return etapesIntermediaires;
    }

    public void setEtapesIntermediaires(List<String> etapesIntermediaires) {
        this.etapesIntermediaires = etapesIntermediaires;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public double getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(double poidsMax) {
        this.poidsMax = poidsMax;
    }

    public double getVolumeMax() {
        return volumeMax;
    }

    public void setVolumeMax(double volumeMax) {
        this.volumeMax = volumeMax;
    }

    public String getTypeMarchandise() {
        return typeMarchandise;
    }

    public void setTypeMarchandise(String typeMarchandise) {
        this.typeMarchandise = typeMarchandise;
    }

    public int getCapaciteDisponible() {
        return capaciteDisponible;
    }

    public void setCapaciteDisponible(int capaciteDisponible) {
        this.capaciteDisponible = capaciteDisponible;
    }


}
