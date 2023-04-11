package fr.mobiservice.service;

import java.util.Optional;

import fr.mobiservice.domain.Client;

public interface ClientService {

    /**
     * @param clientId
     * @return Client par son id
     */
    Optional<Client> findById(Long clientId);
}
