package com.example.projetSoutenance2026PME.exception.produit;

import com.example.projetSoutenance2026PME.exception.ResourceNotFoundException;

public class ProduitNotFoundException extends ResourceNotFoundException {
    public ProduitNotFoundException(String message) {
        super(message);
    }
}
