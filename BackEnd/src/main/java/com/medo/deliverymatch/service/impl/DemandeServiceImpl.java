package com.medo.deliverymatch.service.impl;

import com.medo.deliverymatch.enums.StatutDemande;
import com.medo.deliverymatch.model.Colis;
import com.medo.deliverymatch.model.Demande;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.ColisRepository;
import com.medo.deliverymatch.repository.DemandeRepository;
import com.medo.deliverymatch.repository.TrajetRepository;
import com.medo.deliverymatch.service.DemandeService;
import com.medo.deliverymatch.dto.DemandeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {
    private final DemandeRepository demandeRepository;
    private final TrajetRepository trajetRepository;
    private final ColisRepository colisRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository, TrajetRepository trajetRepository, ColisRepository colisRepository) {
        this.demandeRepository = demandeRepository;
        this.trajetRepository = trajetRepository;
        this.colisRepository = colisRepository;
    }

    @Override
    @Transactional
    public Demande createDemande(Utilisateur expediteur, Trajet trajet, Colis colis) {
        Demande demande = new Demande();
        demande.setExpediteur(expediteur);
        demande.setTrajet(trajet);
        demande.setColis(colis);
        demande.setStatut(StatutDemande.DONE); // or EN_ATTENTE if you want
        return demandeRepository.save(demande);
    }

    @Override
    public List<Demande> getDemandesByExpediteur(Utilisateur expediteur) {
        return demandeRepository.findByExpediteur(expediteur);
    }

    @Override
    @Transactional
    public void cancelDemande(Long demandeId, Utilisateur expediteur) {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
        if (!demande.getExpediteur().getId().equals(expediteur.getId())) {
            throw new SecurityException("Not authorized to cancel this demande");
        }
        // Break the association with Colis before deleting
        if (demande.getColis() != null) {
            Colis colis = demande.getColis();
            colis.setDemande(null);
            colisRepository.save(colis);
        }
        demandeRepository.deleteById(demandeId);
    }

    @Override
    @Transactional
    public Demande updateDemande(Long demandeId, Demande updatedDemande, Utilisateur expediteur) {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande not found"));
        if (!demande.getExpediteur().getId().equals(expediteur.getId())) {
            throw new SecurityException("Not authorized to update this demande");
        }
        // Update fields as needed
        demande.setStatut(updatedDemande.getStatut());
        // Add more fields if needed
        return demandeRepository.save(demande);
    }

    @Override
    public List<DemandeDTO> getDemandeDTOsByExpediteur(Utilisateur expediteur) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return demandeRepository.findByExpediteur(expediteur).stream()
            .peek(demande -> {
                System.out.println("Demande: " + demande.getId() + ", Trajet: " + (demande.getTrajet() != null ? demande.getTrajet().getLieuDepart() : "null") + " → " + (demande.getTrajet() != null ? demande.getTrajet().getDestination() : "null"));
            })
            .map(demande -> new DemandeDTO(
                demande.getId(),
                demande.getStatut().name(),
                demande.getTrajet() != null ? demande.getTrajet().getLieuDepart() + " → " + demande.getTrajet().getDestination() : "",
                demande.getTrajet() != null && demande.getTrajet().getDateDepart() != null ? demande.getTrajet().getDateDepart().toLocalDateTime().toLocalDate().format(formatter) : "",
                demande.getExpediteur() != null ? demande.getExpediteur().getNom() + " " + demande.getExpediteur().getPrenom() : ""
            ))
            .toList();
    }

    @Override
    public List<DemandeDTO> getAllDemandeDTOs() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return demandeRepository.findAll().stream()
            .map(demande -> new DemandeDTO(
                demande.getId(),
                demande.getStatut().name(),
                demande.getTrajet() != null ? demande.getTrajet().getLieuDepart() + " → " + demande.getTrajet().getDestination() : "",
                demande.getTrajet() != null && demande.getTrajet().getDateDepart() != null ? demande.getTrajet().getDateDepart().toLocalDateTime().toLocalDate().format(formatter) : "",
                demande.getExpediteur() != null ? demande.getExpediteur().getNom() + " " + demande.getExpediteur().getPrenom() : ""
            ))
            .toList();
    }
} 