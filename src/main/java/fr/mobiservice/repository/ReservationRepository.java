package fr.mobiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.mobiservice.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT ISNULL(SUM(b.quantity), 0) FROM billet b " +
    "WHERE b.trajet_id_fk = :trajetId", nativeQuery = true)
    Long countNombrePlaceRestante(Long trajetId);
}
