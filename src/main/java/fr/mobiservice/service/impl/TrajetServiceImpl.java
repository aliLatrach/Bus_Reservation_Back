package fr.mobiservice.service.impl;

import java.util.List;
import java.util.Optional;

import fr.mobiservice.repository.TrajetRepository;
import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Trajet;
import fr.mobiservice.service.TrajetService;

@Service
public class TrajetServiceImpl implements TrajetService {

    private TrajetRepository trajetRepository;

    public TrajetServiceImpl(final TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    @Override
    public Optional<Trajet> findById(Long trajetId) {
        return trajetRepository.findById(trajetId);
    }

    @Override
    public List<Trajet> findAllByBusNumber(int busNumber) {
        return trajetRepository.findAllByBusNumber(busNumber);
    }

    @Override
    public List<Trajet> findAllByBusId(int busId) {
        return trajetRepository.findAllByBusId(busId);
    }

    @Override
    public List<Trajet> findAllById(Iterable<Long> trajetsId) {
        return trajetRepository.findAllById(trajetsId);
    }
}
