package com.medo.deliverymatch.repository;

import com.medo.deliverymatch.model.Colis;
import com.medo.deliverymatch.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
    List<Colis> findByExpediteur(Utilisateur expediteur);
}
