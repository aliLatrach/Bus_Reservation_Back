package fr.mobiservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.mobiservice.domain.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {

    Optional<Bus> findByBusNumber(int busNumber);

    void deleteBusByBusNumber(int busNumber);
}
