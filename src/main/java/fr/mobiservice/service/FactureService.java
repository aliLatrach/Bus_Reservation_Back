package fr.mobiservice.service;

import java.util.List;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.TypeMoyenPaiementEnum;

public interface FactureService {

    Facture generateFacture(Reservation reservation, TypeMoyenPaiementEnum moyenPaiement);

    List<Facture> getSortedFactures();
}
