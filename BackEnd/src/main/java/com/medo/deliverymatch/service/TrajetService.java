package com.medo.deliverymatch.service;

import com.medo.deliverymatch.dto.CreateTrajetRequest;
import com.medo.deliverymatch.dto.TrajetDTO;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;

import java.util.List;

public interface TrajetService {
    List<TrajetDTO> getAllTrajets();
    Trajet getTrajetById(Long id);
    TrajetDTO createTrajet(CreateTrajetRequest request, Utilisateur conducteur);
    TrajetDTO updateTrajet(Long id, CreateTrajetRequest request, Utilisateur conducteur);
    void deleteTrajet(Long id, Utilisateur conducteur);
    List<TrajetDTO> getTrajetsByConducteur(Utilisateur conducteur);
}
