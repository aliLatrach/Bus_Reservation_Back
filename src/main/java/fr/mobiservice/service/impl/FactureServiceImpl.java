package fr.mobiservice.service.impl;

import java.util.List;

import fr.mobiservice.repository.FactureRepository;
import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.TypeMoyenPaiementEnum;
import fr.mobiservice.service.FactureService;

@Service
public class FactureServiceImpl implements FactureService {

    private FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture generateFacture(Reservation reservation, TypeMoyenPaiementEnum moyenPaiement) {
        Facture facture = Facture.builder().reservation(reservation).typeMoyenPaiementEnum(moyenPaiement).build();
        return factureRepository.save(facture);
    }

    @Override
    public List<Facture> getSortedFactures() {
        return factureRepository.findByOrderByFactureIdAsc();
    }
}
