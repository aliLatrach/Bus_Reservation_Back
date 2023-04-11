package fr.mobiservice.service.paiement;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.mobiservice.domain.Billet;
import fr.mobiservice.domain.Bus;
import fr.mobiservice.domain.Client;
import fr.mobiservice.domain.Facture;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.Trajet;
import fr.mobiservice.exception.MoyenPaiementNotValidException;
import fr.mobiservice.model.MoyenPaiement;
import fr.mobiservice.service.FactureService;
import fr.mobiservice.service.PaiementService;
import fr.mobiservice.service.impl.paiement.PaiementServiceFactory;
import fr.mobiservice.service.impl.paiement.PaiementServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class PaiementServiceUnitTest {

    @Mock
    private FactureService factureService;

    @Autowired
    private PaiementService paiementService;
    @Autowired
    private PaiementServiceFactory paiementServiceFactory;


    // @Override
    // public Facture payReservation(Reservation reservation, MoyenPaiement moyenPaiement) throws MoyenPaiementNotValidException {
    //     Paiement paiement = paiementFactory.createPaiementByTypeMoyenPaiement(moyenPaiement.getTypeMoyenPaiement())
    //         .orElseThrow(() -> new MoyenPaiementNotValidException("Moyen de paiement invalide"));
    //     return paiement.payReservation(reservation);
    // }

    @BeforeEach
    void setUp() {
        paiementServiceFactory = new PaiementServiceFactory(factureService);
        paiementService = new PaiementServiceImpl(paiementServiceFactory);
    }

    @ParameterizedTest
    @CsvSource({
        //CARTE_BANCAIRE
        "4975123456789, 12/04/2024, ",
        //PAYPAL
        ", , paypal@email.com" 
    })
    public void payReservation_should_sent_facture_when_moyenPaiementAndReservationAreValid(
        String cardNumber, String expirationDate, String email
    ) throws Exception {
        //GIVEN
        Reservation reservation = createSimpleReservation();
        MoyenPaiement moyenPaiement = MoyenPaiement.builder()
            .cardNumber("" + cardNumber)
            .expirationDate("" + expirationDate)
            .email("" + email).build();
    
        //WHEN
        Mockito.when(factureService.generateFacture(reservation, moyenPaiement.getTypeMoyenPaiement()))
            .thenReturn(Facture.builder()
                .factureId(10L).build());
        Facture factureActual = paiementService.payReservation(reservation, moyenPaiement);

        //THEN
        verify(factureService).generateFacture(reservation, moyenPaiement.getTypeMoyenPaiement());
        Assertions.assertNotNull(factureActual);
    }

    private Reservation createSimpleReservation() {
        List<Billet> billets = new ArrayList<Billet>();
        billets.add(Billet.builder().billetId(1L)
            .trajet(Trajet.builder()
                .bus(Bus.builder().busId(1L).build())
                .departureDate(LocalDateTime.now())
                .seatsNumber(12)
                .price(40)
                .trajetId(1L).build())
            .quantity(3).build());
        Reservation reservation = Reservation.builder()
            .client(Client.builder().email("myemail@mail.com").build())
            .billets(billets).build();
        return reservation;
    }

    @ParameterizedTest
    @CsvSource({
        ", 12/04/2024, ",
        "49751234567, , ", 
        ", , ",
    })
    public void payReservation_should_throwsMoyenPaiementNotValidException_when_moyenPaiementIsUnknown(
        String cardNumber, String expirationDate, String email
    ) throws Exception {
        //GIVEN
        Reservation reservation = createSimpleReservation();
        MoyenPaiement moyenPaiement = MoyenPaiement.builder()
            .cardNumber(Objects.toString(cardNumber, ""))
            .expirationDate(Objects.toString(expirationDate, ""))
            .email(Objects.toString(email, "")).build();
    
        //WHEN
        //THEN
        Assertions.assertThrows(MoyenPaiementNotValidException.class, 
            () -> { paiementService.payReservation(reservation, moyenPaiement); });
        verify(factureService, never()).generateFacture(reservation, moyenPaiement.getTypeMoyenPaiement());
    }
}
