package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.Ride;
import sap.ass01.layered.business.RideImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class RideService implements Service<Ride>, InputObserver<RideDTO> {

    private final List<Repository<Ride, String>> repositories;
    private Repository<User, String> userRepository;
    private Repository<EBike, String> eBikeRepository;


    public RideService() {
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        final Optional<User> user = this.userRepository.getObjectByID(newValue.userId());
        final Optional<EBike> eBike = this.eBikeRepository.getObjectByID(newValue.bikeId());
        if (user.isPresent() && eBike.isPresent()) {
            this.repositories.forEach(repo -> {
                final String id = repo.generateNewId();
                final Ride newRide = new RideImpl(id, user.get(), eBike.get());
                repo.save(newRide);
            });
        }

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
