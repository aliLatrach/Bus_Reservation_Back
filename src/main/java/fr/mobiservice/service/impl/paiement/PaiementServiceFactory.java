package fr.mobiservice.service.impl.paiement;

import java.util.Optional;

import fr.mobiservice.domain.TypeMoyenPaiementEnum;
import fr.mobiservice.service.FactureService;

import org.springframework.stereotype.Service;

@Service
public class PaiementServiceFactory {
    
    private FactureService factureService;

    public PaiementServiceFactory (FactureService factureService) {
        this.factureService = factureService;
    }
    
    public Optional<Paiement> createPaiementByTypeMoyenPaiement (TypeMoyenPaiementEnum typeMoyenPaiement) {
        switch (typeMoyenPaiement) {
            case CARTE_BANCAIRE :
                return Optional.of(new PaiementByCard(factureService));
            case PAYPAL :
                return Optional.of(new PaiementByPaypal(factureService));
            case NON_RECONNU :
            default:
                return Optional.empty();
        }
    }
}
