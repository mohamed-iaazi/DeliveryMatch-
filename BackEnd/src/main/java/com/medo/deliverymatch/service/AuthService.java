package com.medo.deliverymatch.service;

import com.medo.deliverymatch.dto.AuthRequest;
import com.medo.deliverymatch.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse register(AuthRequest authRequest);
    AuthResponse registerConducteur(AuthRequest authRequest);
}
