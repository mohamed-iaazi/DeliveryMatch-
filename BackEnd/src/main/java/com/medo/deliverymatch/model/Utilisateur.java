package com.medo.deliverymatch.model;

import com.medo.deliverymatch.enums.Role;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utilisateurs")
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean verifie;

    private String tokenVerification;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @Column(name = "date_mise_a_jour")
    private Timestamp dateMiseAJour;

    @OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Trajet> trajets;

    @OneToMany(mappedBy = "expediteur", cascade = CascadeType.ALL)
    private List<RequeteLivraison> requetesLivraison;

    @OneToMany(mappedBy = "expediteur", cascade = CascadeType.ALL)
    private List<Demande> demandes;

    public Utilisateur(String prenom, String nom, String login, String motDePasse, Role role) {
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
        this.dateCreation = new Timestamp(System.currentTimeMillis());
        this.dateMiseAJour = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verifie;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isVerifie() {
        return verifie;
    }

    public void setVerifie(boolean verifie) {
        this.verifie = verifie;
    }

    public String getTokenVerification() {
        return tokenVerification;
    }

    public void setTokenVerification(String tokenVerification) {
        this.tokenVerification = tokenVerification;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Timestamp getDateMiseAJour() {
        return dateMiseAJour;
    }

    public void setDateMiseAJour(Timestamp dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public List<Trajet> getTrajets() {
        return trajets;
    }

    public void setTrajets(List<Trajet> trajets) {
        this.trajets = trajets;
    }

    public List<RequeteLivraison> getRequetesLivraison() {
        return requetesLivraison;
    }

    public void setRequetesLivraison(List<RequeteLivraison> requetesLivraison) {
        this.requetesLivraison = requetesLivraison;
    }

    public List<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }

    public void updateLastModification() {
        this.dateMiseAJour = new Timestamp(System.currentTimeMillis());
    }
}
