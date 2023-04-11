package fr.mobiservice.service;

import java.util.List;
import java.util.Optional;

import fr.mobiservice.domain.Trajet;

public interface TrajetService {


    /**
     * @param trajetId
     * @return le Trajet par son identifiant
     */
    Optional<Trajet> findById(Long trajetId);

    /**
     * @param busNumber
     * @return la liste des Trajets par numero de Bus
     */
    List<Trajet> findAllByBusNumber(int busNumber);

    /**
     * @param busId
     * @return la liste des Trajets par BusId
     */
    List<Trajet> findAllByBusId(int busId);

    /**
     * @param busId
     * @return la liste des Trajets par BusId
     */
    List<Trajet> findAllById(Iterable<Long> trajetsId);

}
