package fr.mobiservice.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.mobiservice.domain.Billet;
import fr.mobiservice.domain.Client;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.domain.Trajet;
import fr.mobiservice.dto.CreateReservationDto;
import fr.mobiservice.dto.ReservationDto;
import fr.mobiservice.exception.ClientNotFoundException;
import fr.mobiservice.exception.NombrePlaceInsuffisantException;
import fr.mobiservice.exception.TrajetNotFoundException;
import fr.mobiservice.service.ClientService;
import fr.mobiservice.service.ReservationService;
import fr.mobiservice.service.TrajetService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    private ReservationService reservationService;
    private TrajetService trajetService;
    private ClientService clientService;

    public ReservationController(final ReservationService reservationService,
            final ClientService clientService,
            final TrajetService trajetService) {
        this.reservationService = reservationService;
        this.clientService = clientService;
        this.trajetService = trajetService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDto> create(final @RequestBody CreateReservationDto reservation) 
        throws ClientNotFoundException, TrajetNotFoundException, IllegalArgumentException, NombrePlaceInsuffisantException {
        if (reservation.getBillets().isEmpty() || Objects.isNull(reservation.getClientId())) {
            throw new IllegalArgumentException();
        }

        List<Billet> billetsEntity = reservation.getBillets().stream().map(billetDto -> {
            Trajet trajet = trajetService.findById(billetDto.getTrajetId())
                .orElseThrow(TrajetNotFoundException::new);
            return Billet.builder()
                .quantity(billetDto.getQuantity())
                .trajet(trajet)
                .build();
            }).collect(Collectors.toList());
        Client client = clientService.findById(reservation.getClientId())
            .orElseThrow(() -> new ClientNotFoundException(reservation.getClientId()));
        Reservation reservationToSave = Reservation.builder()
            .client(client)
            .billets(billetsEntity).build();
        Reservation createdReservation = reservationService.create(reservationToSave);
        return ResponseEntity.status(CREATED).body(ReservationDto.fromEntity(createdReservation));
    }

    @GetMapping("{id}")
    public ResponseEntity<Reservation> findByNumero(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findBy(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteBy(id);
        return ResponseEntity.noContent().build();
    }
}
