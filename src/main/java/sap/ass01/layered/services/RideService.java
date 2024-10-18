package sap.ass01.layered.services;

import sap.ass01.layered.business.Ride;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;

import java.util.List;
import java.util.ArrayList;

public class RideService implements Service<Ride>, InputObserver<RideDTO> {

    private final List<Repository<Ride, ?>> repositories;

    public RideService() {
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        // Logic for create a new ride given the DTO
    }

    @Override
    public void add(final Ride newValue) {
        this.repositories.forEach(r -> r.save(newValue));
    }

    @Override
    public <R extends Repository<Ride, ?>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
