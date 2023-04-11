package fr.mobiservice.service;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.exception.MoyenPaiementNotValidException;
import fr.mobiservice.model.MoyenPaiement;

public interface PaiementService {

    /**
     * @param reservation
     * @return
     * @throws MoyenPaiementNotValidException
     */
    Facture payReservation(Reservation reservation, MoyenPaiement moyenPaiement) throws MoyenPaiementNotValidException;
}
