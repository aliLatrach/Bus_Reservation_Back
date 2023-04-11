package fr.mobiservice.service.impl.paiement;

import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.TypeMoyenPaiementEnum;
import fr.mobiservice.service.FactureService;

@Service
public class PaiementByPaypal implements Paiement {

    private FactureService factureService;

    public PaiementByPaypal(FactureService factureService) {
        this.factureService = factureService;
    }

    public Facture payReservation(Reservation reservation) {
        return factureService.generateFacture(reservation, TypeMoyenPaiementEnum.PAYPAL);
    }
}
