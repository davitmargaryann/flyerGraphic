package com.example.flyergraphic.client.service;

import com.example.flyergraphic.client.models.Client;
import com.example.flyergraphic.dto.SaveClientRequestDto;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Optional<Client> findById(UUID id);

    Client save(Client client);

    List<Client> findAll();

    void saveClientInformation(SaveClientRequestDto saveClientRequestDto);

    void getClientDetails(ModelMap modelMap, String id);

    List<Client> findByNameContaining(String name);

    void deleteById(UUID id);
}
