package fr.mobiservice.service.impl.paiement;

import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.exception.MoyenPaiementNotValidException;
import fr.mobiservice.model.MoyenPaiement;
import fr.mobiservice.service.PaiementService;

@Service
public class PaiementServiceImpl implements PaiementService {

    private PaiementServiceFactory paiementServiceFactory;

    public PaiementServiceImpl(PaiementServiceFactory paiementServiceFactory) {
        this.paiementServiceFactory = paiementServiceFactory;
    }

    @Override
    public Facture payReservation(Reservation reservation, MoyenPaiement moyenPaiement) throws MoyenPaiementNotValidException {
        Paiement paiement = paiementServiceFactory.createPaiementByTypeMoyenPaiement(moyenPaiement.getTypeMoyenPaiement())
            .orElseThrow(() -> new MoyenPaiementNotValidException("Moyen de paiement invalide"));
        return paiement.payReservation(reservation);
    }
}
