package com.medo.deliverymatch.service;

import com.medo.deliverymatch.dto.AuthRequest;
import com.medo.deliverymatch.dto.AuthResponse;
import com.medo.deliverymatch.enums.Role;
import com.medo.deliverymatch.model.Utilisateur;
import com.medo.deliverymatch.repository.UtilisateurRepository;
import com.medo.deliverymatch.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {
        return registerUser(request, Role.UTILISATEUR);
    }

    @Override
    public AuthResponse registerConducteur(AuthRequest request) {
        return registerUser(request, Role.CONDUCTEUR);
    }

    private AuthResponse registerUser(AuthRequest request, Role role) {
        var user = Utilisateur.builder()
                .login(request.getLogin())
                .motDePasse(passwordEncoder.encode(request.getPassword()))
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(role)
                .verifie(true)
                .build();
        var savedUser = utilisateurRepository.save(user);
        var jwtToken = jwtUtil.generateToken(savedUser);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(savedUser.getPrenom())
                .role(savedUser.getRole().name())
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        var user = utilisateurRepository.findByLogin(request.getLogin()).orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getPrenom())
                .role(user.getRole().name())
                .build();
    }
}
