package fr.mobiservice.service;

import java.util.List;
import java.util.Optional;

import fr.mobiservice.domain.Reservation;

public interface ReservationService {

    /**
     * @param reservation
     * @return inserer une nouvelle reservation
     */
    Reservation create(Reservation reservation);

    /**
     * @return la liste des reservations
     */
    List<Reservation> findAll();

    /**
     * @param id
     * @return Reservation par id
     */
    Optional<Reservation> findBy(Long id);

    /**
     * Supprimer une reservation par son id
     * @param id
     */
    void deleteBy(Long id);
}
