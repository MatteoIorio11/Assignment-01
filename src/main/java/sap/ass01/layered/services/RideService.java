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

public class RideService implements Service<Ride, String>, InputObserver<RideDTO> {

    private final List<Repository<Ride, String>> repositories;
    private final Repository<User, String> userRepository;
    private final Repository<EBike, String> eBikeRepository;


    public RideService(final Repository<User, String> userRepository, final Repository<EBike, String> eBikeRepository) {
        this.repositories = new ArrayList<>();
        this.userRepository = userRepository;
        this.eBikeRepository = eBikeRepository;
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        final Optional<User> user = this.userRepository.getObjectByID(newValue.userId());
        final Optional<EBike> eBike = this.eBikeRepository.getObjectByID(newValue.bikeId());
        if (user.isPresent() && eBike.isPresent()) {
            this.repositories.forEach(repo -> {
                final String id = repo.generateNewId();
                final Ride newRide = new RideImpl(id, user.get(), eBike.get());
                this.add(newRide);
            });
        }
    }

    @Override
    public void add(final Ride newValue) {
        this.repositories.forEach(r -> r.save(newValue));
    }

    @Override
    public <R extends Repository<Ride, String>> void addRepository(R repository) {
        this.repositories.add(repository);
    }
}
