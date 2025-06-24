package com.medo.deliverymatch.service;

import com.medo.deliverymatch.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    public Utilisateur createAccount(Utilisateur utilisateur);
    public Utilisateur updateAccount(Utilisateur utilisateur);
    public void deleteAccount(Utilisateur utilisateur);
    public List<Utilisateur> getAllAccounts();


}
