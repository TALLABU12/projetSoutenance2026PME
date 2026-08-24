package com.example.projetSoutenance2026PME.service;

import com.example.projetSoutenance2026PME.dto.ClientRequest;
import com.example.projetSoutenance2026PME.dto.ClientResponse;
import com.example.projetSoutenance2026PME.entity.Client;
import com.example.projetSoutenance2026PME.exception.ClientFoundException;
import com.example.projetSoutenance2026PME.exception.ClientNotFoundException;
import com.example.projetSoutenance2026PME.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getCode(),
                client.getNom(),
                client.getTelephone(),
                client.getEmail(),
                client.getAdresse(),
                client.getDateCreation(),
                client.getDateModification(),
                client.isActif()
        );
    }

    @Transactional
    public ClientResponse ajouterClient(ClientRequest request){
        boolean clientOptional = clientRepository.existsByCode(request.getCode());
        if (clientOptional){
            throw new ClientFoundException("Client existe deja");
        }
        Client client = new Client(request.getCode(), request.getNom(), request.getTelephone(), request.getEmail(), request.getAdresse());
        Client save = clientRepository.save(client);
        return toResponse(save);
    }

    @Transactional
    public ClientResponse modifierClient(Long id , ClientRequest request){
        Client client = clientRepository.findById(id).orElseThrow(
                ()-> new ClientNotFoundException("Client introuvable")
        );
        client.setNom(request.getNom());
        client.setTelephone(request.getTelephone());
        client.setEmail(request.getEmail());
        client.setAdresse(request.getAdresse());
        return toResponse(client);
    }

    @Transactional
    public List<ClientResponse> listerClients(){
        return clientRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    @Transactional
    public void supprimerClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
                ()-> new ClientNotFoundException("Client introuvable")
        );
        client.setActif(false);
    }

    @Transactional
    public ClientResponse chercherParId(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
                ()-> new ClientNotFoundException("Client introuvable")
        );
        return toResponse(client);
    }
}
