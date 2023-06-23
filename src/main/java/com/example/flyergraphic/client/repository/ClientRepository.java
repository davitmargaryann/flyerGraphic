package com.example.flyergraphic.client.repository;

import com.example.flyergraphic.client.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findById(UUID id);

    Client save(Client client);

    List<Client> findAll();

    List<Client> findByFullNameContaining(String name);

    void deleteById(UUID id);

}
