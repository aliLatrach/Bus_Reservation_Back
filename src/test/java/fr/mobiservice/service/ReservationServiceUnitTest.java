package fr.mobiservice.service;

import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.Trajet;
import fr.mobiservice.repository.ReservationRepository;
import fr.mobiservice.service.impl.ReservationServiceImpl;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import fr.mobiservice.domain.Billet;
import fr.mobiservice.domain.Bus;
import fr.mobiservice.domain.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceUnitTest  {

    private static Client CLIENT;

    @Mock
    private ReservationRepository reservationRepository;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        CLIENT = Client.builder().email("myemail@mail.com").build();
    
        reservationService = new ReservationServiceImpl(reservationRepository);
    } 

    @Test
    public void create_should_saveAndReturnTheReservation_when_reservationContainsTrajets() {
        //GIVEN
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
            .client(CLIENT)
            .billets(billets).build();

        //WHEN
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);
        Reservation result = reservationService.create(reservation);

        //THEN
        verify(reservationRepository).save(reservation);
        Assertions.assertNotNull(result);
    }

    @Test
    public void create_should_throwExeption_when_reservationHasNoTrajet() {
        //GIVEN
        List<Billet> billets = new ArrayList<Billet>();
        Reservation reservation = Reservation.builder()
            .client(CLIENT)
            .billets(billets).build();

        //WHEN
        Mockito.when(reservationRepository.save(Mockito.any())).thenThrow(DataIntegrityViolationException.class);
        
        //THEN
        Assertions.assertThrows(DataAccessException.class, 
            () -> { reservationService.create(reservation); });
        verify(reservationRepository).save(reservation);    
    }    

    @Test
    public void findAll_should_retrieveAllReservations_when_reservationsExist() {
        //GIVEN
        List<Reservation> mockedReservations = new ArrayList<Reservation>();
        mockedReservations.add(Reservation.builder().build());
        mockedReservations.add(Reservation.builder().build());

        //WHEN
        Mockito.when(reservationRepository.findAll()).thenReturn(mockedReservations);
        List<Reservation> actualReservations = reservationService.findAll();
    
        //THEN
        Assertions.assertFalse(actualReservations.isEmpty());
        verify(reservationRepository).findAll();
    }

    @Test
    public void findAll_should_returnEmptyList_when_thereIsNoReservation() {
        //GIVEN
        List<Reservation> mockedEmptyList = Collections.emptyList();

        //WHEN
        Mockito.when(reservationService.findAll()).thenReturn(mockedEmptyList);
        List<Reservation> actualResult = reservationService.findAll();
        
        //THEN
        Assertions.assertTrue(actualResult.isEmpty());
        verify(reservationRepository).findAll();    
    }    

    @Test
    public void findBy_should_returnOneReservation_when_theReservationIdExists() {
        //GIVEN
        List<Billet> billets = new ArrayList<Billet>();
        billets.add(Billet.builder().billetId(1L)
            .trajet(Trajet.builder()
                .bus(Bus.builder().busId(1L).build())
                .departureDate(LocalDateTime.now())
                .seatsNumber(12)
                .price(40)
                .trajetId(1L).build())
            .quantity(3).build());
        Reservation actualReservation = Reservation.builder()
            .client(CLIENT)
            .billets(billets).build();
        Long reservationId = 1L;

        //WHEN
        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(actualReservation));
        Optional<Reservation> result = reservationService.findBy(reservationId);

        //THEN
        Assertions.assertTrue(result.isPresent());
        verify(reservationRepository).findById(reservationId);
    }

    @Test
    public void findBy_should_returnEmptyReservation_when_theReservationIdDoesNotExist() {
        //GIVEN
        Long reservationId = 1L;

        //WHEN
        Mockito.when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());
        Optional<Reservation> result = reservationService.findBy(reservationId);

        //THEN
        Assertions.assertTrue(result.isEmpty());
        verify(reservationRepository).findById(reservationId);
    }

    @Test
    public void deleteBy_should_deleteReservation_when_theReservationIdExists() {
        //GIVEN
        Long reservationId = 1L;

        //WHEN
        Mockito.doNothing().when(reservationRepository).deleteById(reservationId);

        //THEN
        Assertions.assertDoesNotThrow(() -> reservationService.deleteBy(reservationId));
        verify(reservationRepository).deleteById(reservationId);
    }

    @Test
    public void deleteBy_should_throwsAnException_when_theReservationIdDoesNotExist() {
        //GIVEN
        Long reservationId = 1L;

        //WHEN
        Mockito.doThrow(EmptyResultDataAccessException.class).when(reservationRepository).deleteById(reservationId);

        //THEN
        Assertions.assertThrows(DataAccessException.class, () -> reservationService.deleteBy(reservationId));
        verify(reservationRepository).deleteById(reservationId);
    }

    @Test
    public void deleteBy_should_throwsAnException_when_theReservationIdNull() {
        //GIVEN
        Long reservationId = null;

        //WHEN
        Mockito.doThrow(IllegalArgumentException.class).when(reservationRepository).deleteById(reservationId);

        //THEN
        Assertions.assertThrows(IllegalArgumentException.class, () -> reservationService.deleteBy(reservationId));
        verify(reservationRepository).deleteById(reservationId);
    }
}