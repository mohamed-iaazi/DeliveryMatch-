package com.medo.deliverymatch.controller;

import com.medo.deliverymatch.model.Colis;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.ColisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/colis")
public class ColisController {
    private final ColisRepository colisRepository;

    public ColisController(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    @GetMapping("/my")
    public ResponseEntity<List<Colis>> getMyColis(@AuthenticationPrincipal Utilisateur expediteur) {
        return ResponseEntity.ok(colisRepository.findByExpediteur(expediteur));
    }

    @PostMapping
    public ResponseEntity<Colis> createColis(@RequestBody Colis colis, @AuthenticationPrincipal Utilisateur expediteur) {
        colis.setExpediteur(expediteur);
        colis.setDateCreation(LocalDate.now());
        Colis savedColis = colisRepository.save(colis);
        return ResponseEntity.ok(savedColis);
    }
}
