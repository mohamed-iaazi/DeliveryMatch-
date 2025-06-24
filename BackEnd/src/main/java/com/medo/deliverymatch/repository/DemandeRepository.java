package com.medo.deliverymatch.repository;

import com.medo.deliverymatch.model.Demande;
import com.medo.deliverymatch.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    @Query("SELECT d FROM Demande d JOIN FETCH d.trajet WHERE d.expediteur = :expediteur")
    List<Demande> findByExpediteur(@Param("expediteur") Utilisateur expediteur);
} 