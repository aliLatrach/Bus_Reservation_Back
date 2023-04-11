package fr.mobiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.mobiservice.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
