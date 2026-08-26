package com.example.projetSoutenance2026PME.exception.categorie;

import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;

public class CategorieNotFoundException extends ResourceNotFoundException {
    public CategorieNotFoundException(String message) {
        super(message);
    }
}
