package com.example.projetSoutenance2026PME.controller;

import com.example.projetSoutenance2026PME.dto.client.ClientRequest;
import com.example.projetSoutenance2026PME.dto.client.ClientResponse;
import com.example.projetSoutenance2026PME.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientResponse> listerClients(){
        return clientService.listerClients();
    }

    @GetMapping("/{id}")
    public ClientResponse rechercherClientId(@PathVariable Long id){
        return clientService.chercherParId(id);
    }

    @DeleteMapping("/{id}")
    public void supprimerClient(@PathVariable Long id){
        clientService.supprimerClient(id);
    }

    @PutMapping("/{id}")
    public ClientResponse modifierClient(@PathVariable Long id, @Valid @RequestBody ClientRequest request){
        return clientService.modifierClient(id,request);
    }

    @PostMapping
    public ClientResponse ajouterClient(@Valid @RequestBody ClientRequest request){
        return clientService.ajouterClient(request);
    }

}

