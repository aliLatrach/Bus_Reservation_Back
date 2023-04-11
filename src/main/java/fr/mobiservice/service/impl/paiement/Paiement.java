package fr.mobiservice.service.impl.paiement;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;

public interface Paiement {

    public Facture payReservation(Reservation reservation);
    
}
