package com.medo.deliverymatch.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int note;

    private String commentaire;

    @Column(nullable = false)
    private LocalDate dateEvaluation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utilisateur utilisateurEvalue;

    @ManyToOne
    private Colis colis;

    @ManyToOne
    private Trajet trajet;
}
