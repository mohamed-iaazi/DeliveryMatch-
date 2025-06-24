package com.medo.deliverymatch.repository;

import com.medo.deliverymatch.model.Trajet;
import com.medo.deliverymatch.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findByActif(boolean actif);
    List<Trajet> findByConducteur(Utilisateur conducteur);
}
