package fr.mobiservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.mobiservice.domain.Trajet;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {

    @Query("SELECT t FROM Trajet t " +
        "LEFT JOIN t.bus b " +
        "WHERE b.busNumber = :busNumber")
    List<Trajet> findAllByBusNumber(int busNumber);

    @Query("SELECT t FROM Trajet t " +
        "LEFT JOIN t.bus b " +
        "WHERE b.busId = :busId")
    List<Trajet> findAllByBusId(int busId);
}
