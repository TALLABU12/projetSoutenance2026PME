package com.example.projetSoutenance2026PME.exception.client;

import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;

public class ClientNotFoundException extends ResourceNotFoundException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
