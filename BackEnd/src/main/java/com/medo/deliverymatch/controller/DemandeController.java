package com.medo.deliverymatch.controller;

import com.medo.deliverymatch.model.Colis;
import com.medo.deliverymatch.model.Demande;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.ColisRepository;
import com.medo.deliverymatch.repository.TrajetRepository;
import com.medo.deliverymatch.service.DemandeService;
import com.medo.deliverymatch.dto.DemandeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {
    private final DemandeService demandeService;
    private final TrajetRepository trajetRepository;
    private final ColisRepository colisRepository;

    @Autowired
    public DemandeController(DemandeService demandeService, TrajetRepository trajetRepository, ColisRepository colisRepository) {
        this.demandeService = demandeService;
        this.trajetRepository = trajetRepository;
        this.colisRepository = colisRepository;
    }

    @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestParam Long trajetId, @RequestParam Long colisId, @AuthenticationPrincipal Utilisateur expediteur) {
        Trajet trajet = trajetRepository.findById(trajetId).orElseThrow(() -> new RuntimeException("Trajet not found"));
        Colis colis = colisRepository.findById(colisId).orElseThrow(() -> new RuntimeException("Colis not found"));
        Demande demande = demandeService.createDemande(expediteur, trajet, colis);
        return ResponseEntity.ok(demande);
    }

    @GetMapping("/my")
    public ResponseEntity<List<DemandeDTO>> getMyDemandes(@AuthenticationPrincipal Utilisateur expediteur) {
        return ResponseEntity.ok(demandeService.getDemandeDTOsByExpediteur(expediteur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelDemande(@PathVariable Long id, @AuthenticationPrincipal Utilisateur expediteur) {
        demandeService.cancelDemande(id, expediteur);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Demande> updateDemande(@PathVariable Long id, @RequestBody Demande updatedDemande, @AuthenticationPrincipal Utilisateur expediteur) {
        return ResponseEntity.ok(demandeService.updateDemande(id, updatedDemande, expediteur));
    }
} 