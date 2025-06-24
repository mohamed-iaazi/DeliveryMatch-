package com.medo.deliverymatch.service;

import com.medo.deliverymatch.model.Demande;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Colis;
import java.util.List;
import com.medo.deliverymatch.dto.DemandeDTO;

public interface DemandeService {
    Demande createDemande(Utilisateur expediteur, Trajet trajet, Colis colis);
    List<Demande> getDemandesByExpediteur(Utilisateur expediteur);
    void cancelDemande(Long demandeId, Utilisateur expediteur);
    Demande updateDemande(Long demandeId, Demande updatedDemande, Utilisateur expediteur);
    List<DemandeDTO> getDemandeDTOsByExpediteur(Utilisateur expediteur);
} 