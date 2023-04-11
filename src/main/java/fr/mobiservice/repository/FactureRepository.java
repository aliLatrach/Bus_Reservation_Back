package fr.mobiservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.mobiservice.domain.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findByOrderByFactureIdAsc();
}
