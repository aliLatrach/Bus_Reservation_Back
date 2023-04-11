package fr.mobiservice.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import fr.mobiservice.domain.Billet;
import fr.mobiservice.domain.Reservation;
import fr.mobiservice.exception.NombrePlaceInsuffisantException;
import fr.mobiservice.repository.ReservationRepository;
import fr.mobiservice.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public Reservation create(Reservation reservation) {
        reservation.getBillets().stream().forEach(this::verifierDisponibilitePlace);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findBy(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteBy(Long id) {
        reservationRepository.deleteById(id);
    }

    private void verifierDisponibilitePlace(Billet billet) {
        int nbPlaceTotal = billet.getTrajet().getSeatsNumber();
        Long nbPlaceReserve = reservationRepository.countNombrePlaceRestante(billet.getTrajet().getTrajetId());
        int nbPlaceDemande = billet.getQuantity();
        int nbPlaceRestante = nbPlaceTotal - (int) nbPlaceReserve.longValue();
        if ( nbPlaceDemande >  nbPlaceRestante) {
            throw new NombrePlaceInsuffisantException(billet.getTrajet().toString(), nbPlaceRestante, billet.getQuantity());
        }
    }
}
