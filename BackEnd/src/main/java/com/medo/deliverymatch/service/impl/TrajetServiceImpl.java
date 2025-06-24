package com.medo.deliverymatch.service.impl;

import com.medo.deliverymatch.service.TrajetService;
import com.medo.deliverymatch.dto.CreateTrajetRequest;
import com.medo.deliverymatch.dto.TrajetDTO;
import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.TrajetRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajetServiceImpl implements TrajetService {

    private final TrajetRepository trajetRepository;

    public TrajetServiceImpl(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    @Override
    public List<TrajetDTO> getAllTrajets() {
        return trajetRepository.findByActif(true).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Trajet getTrajetById(Long id) {
        return trajetRepository.findById(id).orElse(null);
    }

    @Override
    public TrajetDTO createTrajet(CreateTrajetRequest request, Utilisateur conducteur) {
        Trajet trajet = new Trajet();
        trajet.setLieuDepart(request.getLieuDepart());
        trajet.setDestination(request.getDestination());
        trajet.setDateDepart(Timestamp.valueOf(request.getDateDepart().atStartOfDay()));
        trajet.setPoidsMax(request.getPoidsMax());
        trajet.setLongueurMax(request.getLongueurMax());
        trajet.setLargeurMax(request.getLargeurMax());
        trajet.setHauteurMax(request.getHauteurMax());
        trajet.setTypeColis(Trajet.TypeColis.valueOf(request.getTypeColis()));
        trajet.setConducteur(conducteur);
        trajet.setActif(true);
        trajet.setDateCreation(new Timestamp(System.currentTimeMillis()));
        trajet.setDateMiseAJour(new Timestamp(System.currentTimeMillis()));

        Trajet savedTrajet = trajetRepository.save(trajet);
        return convertToDto(savedTrajet);
    }

    @Override
    public TrajetDTO updateTrajet(Long id, CreateTrajetRequest request, Utilisateur conducteur) {
        Trajet trajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet not found"));

        if (!trajet.getConducteur().getId().equals(conducteur.getId())) {
            throw new SecurityException("You are not authorized to update this trajet");
        }

        trajet.setLieuDepart(request.getLieuDepart());
        trajet.setDestination(request.getDestination());
        trajet.setDateDepart(Timestamp.valueOf(request.getDateDepart().atStartOfDay()));
        trajet.setPoidsMax(request.getPoidsMax());
        trajet.setLongueurMax(request.getLongueurMax());
        trajet.setLargeurMax(request.getLargeurMax());
        trajet.setHauteurMax(request.getHauteurMax());
        trajet.setTypeColis(Trajet.TypeColis.valueOf(request.getTypeColis()));
        trajet.setDateMiseAJour(new Timestamp(System.currentTimeMillis()));

        Trajet updatedTrajet = trajetRepository.save(trajet);
        return convertToDto(updatedTrajet);
    }

    @Override
    public void deleteTrajet(Long id, Utilisateur conducteur) {
        Trajet trajet = trajetRepository.findById(id).orElseThrow(() -> new RuntimeException("Trajet not found"));
        if (!trajet.getConducteur().getId().equals(conducteur.getId())) {
            throw new SecurityException("You are not authorized to delete this trajet");
        }
        trajetRepository.deleteById(id);
    }

    @Override
    public List<TrajetDTO> getTrajetsByConducteur(Utilisateur conducteur) {
        return trajetRepository.findByConducteur(conducteur).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TrajetDTO convertToDto(Trajet trajet) {
        TrajetDTO trajetDTO = new TrajetDTO();
        trajetDTO.setId(trajet.getId());
        trajetDTO.setLieuDepart(trajet.getLieuDepart());
        trajetDTO.setDestination(trajet.getDestination());
        trajetDTO.setDateDepart(trajet.getDateDepart().toLocalDateTime().toLocalDate());
        trajetDTO.setPoidsMax(trajet.getPoidsMax());
        trajetDTO.setVolumeMax(trajet.getHauteurMax() * trajet.getLargeurMax() * trajet.getLongueurMax());
        trajetDTO.setTypeMarchandise(trajet.getTypeColis().name());
        
        // Split etapesIntermediaires string into a list
        if (trajet.getEtapesIntermediaires() != null && !trajet.getEtapesIntermediaires().isEmpty()) {
            trajetDTO.setEtapesIntermediaires(List.of(trajet.getEtapesIntermediaires().split(",")));
        } else {
            trajetDTO.setEtapesIntermediaires(Collections.emptyList());
        }

        return trajetDTO;
    }
}
