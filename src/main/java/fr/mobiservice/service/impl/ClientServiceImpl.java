package fr.mobiservice.service.impl;

import java.util.Optional;

import fr.mobiservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Client;
import fr.mobiservice.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> findById(Long clientId) {
        return clientRepository.findById(clientId);
    }
}
