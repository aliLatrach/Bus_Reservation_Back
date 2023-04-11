package fr.mobiservice.service;

import java.util.List;
import java.util.Optional;

import fr.mobiservice.domain.Bus;

public interface BusService {

    /**
     * @param bus
     * @return inserer un nouveau bus
     */
    Bus create(Bus bus);

    /**
     * @return la liste des bus
     */
    List<Bus> findAll();

    /**
     * @param numero
     * @return Bus par son numero
     */
    Optional<Bus> findBy(int numero);

    /**
     * Supprimer un bus par son numéro
     * @param numero
     */
    void deleteBy(int numero);
}
