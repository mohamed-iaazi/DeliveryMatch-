package com.medo.deliverymatch.service.impl;

import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.UtilisateurRepository;
import com.medo.deliverymatch.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    UtilisateurRepository utilisateurRepository;

    @Autowired
    public  UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;

    }
    @Override
    public Utilisateur createAccount(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateAccount(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public void deleteAccount(Utilisateur utilisateur) {
         utilisateurRepository.delete(utilisateur);
    }

    @Override
    public List<Utilisateur> getAllAccounts() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }
}
