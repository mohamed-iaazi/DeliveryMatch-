package com.medo.deliverymatch.controller;

import com.medo.deliverymatch.dto.CreateTrajetRequest;
import com.medo.deliverymatch.dto.TrajetDTO;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.service.TrajetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    @GetMapping
    public ResponseEntity<List<TrajetDTO>> getAllTrajets() {
        return ResponseEntity.ok(trajetService.getAllTrajets());
    }

    @GetMapping("/my-trajets")
    public ResponseEntity<List<TrajetDTO>> getMyTrajets(@AuthenticationPrincipal Utilisateur conducteur) {
        return ResponseEntity.ok(trajetService.getTrajetsByConducteur(conducteur));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrajetDTO> getTrajetById(@PathVariable Long id) {
        Trajet trajet = trajetService.getTrajetById(id);
        if (trajet == null) {
            return ResponseEntity.notFound().build();
        }
        // This is a temporary DTO conversion. Ideally, the service should return a DTO.
        TrajetDTO trajetDTO = new TrajetDTO();
        trajetDTO.setLieuDepart(trajet.getLieuDepart());
        trajetDTO.setDestination(trajet.getDestination());
        trajetDTO.setDateDepart(trajet.getDateDepart().toLocalDateTime().toLocalDate());
        trajetDTO.setPoidsMax(trajet.getPoidsMax());
        trajetDTO.setVolumeMax(trajet.getHauteurMax() * trajet.getLargeurMax() * trajet.getLongueurMax());
        trajetDTO.setTypeMarchandise(trajet.getTypeColis().name());
        return ResponseEntity.ok(trajetDTO);
    }

    @PostMapping
    public ResponseEntity<TrajetDTO> createTrajet(@RequestBody CreateTrajetRequest request, @AuthenticationPrincipal Utilisateur conducteur) {
        return ResponseEntity.ok(trajetService.createTrajet(request, conducteur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrajetDTO> updateTrajet(@PathVariable Long id, @RequestBody CreateTrajetRequest request, @AuthenticationPrincipal Utilisateur conducteur) {
        return ResponseEntity.ok(trajetService.updateTrajet(id, request, conducteur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id, @AuthenticationPrincipal Utilisateur conducteur) {
        trajetService.deleteTrajet(id, conducteur);
        return ResponseEntity.noContent().build();
    }
}
