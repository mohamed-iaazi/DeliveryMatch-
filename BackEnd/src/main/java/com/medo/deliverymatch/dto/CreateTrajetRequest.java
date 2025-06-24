package com.medo.deliverymatch.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTrajetRequest {
    private String lieuDepart;
    private String destination;
    private LocalDate dateDepart;
    private double poidsMax;
    private double longueurMax;
    private double largeurMax;
    private double hauteurMax;
    private String typeColis;
} 