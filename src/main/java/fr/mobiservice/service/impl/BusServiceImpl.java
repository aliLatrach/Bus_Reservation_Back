package fr.mobiservice.service.impl;

import java.util.List;
import java.util.Optional;

import fr.mobiservice.repository.BusRepository;
import org.springframework.stereotype.Service;

import fr.mobiservice.domain.Bus;
import fr.mobiservice.service.BusService;

@Service
public class BusServiceImpl implements BusService {

    private BusRepository busRepository;

    public BusServiceImpl(final BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus create(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public List<Bus> findAll() {
        return busRepository.findAll();
    }

    @Override
    public Optional<Bus> findBy(int busNumber) {
        return busRepository.findByBusNumber(busNumber);
    }

    @Override
    public void deleteBy(int busNumber) {
        busRepository.deleteBusByBusNumber(busNumber);
    }
}
