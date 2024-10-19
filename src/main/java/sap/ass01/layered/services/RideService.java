package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.Ride;
import sap.ass01.layered.business.RideImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.observers.InputObserver;
import sap.ass01.layered.services.dto.RideDTO;

import java.util.*;

public class RideService implements Service<Ride, String>, InputObserver<RideDTO> {

    private final List<Repository<Ride, String>> repositories;
    private final UserService userService;
    private final EBikeService eBikeService;

    public RideService(final UserService userService, final EBikeService eBikeService) {
        this.repositories = new ArrayList<>();
        this.userService = userService;
        this.eBikeService = eBikeService;
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        final Optional<User> user = this.userService.getById(newValue.userId());
        final Optional<EBike> eBike = this.eBikeService.getById(newValue.bikeId());
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
    public void update(final Ride updatedValue) {
        this.repositories.forEach(r -> r.update(updatedValue));
    }

    @Override
    public Iterable<Ride> getAll() {
        return this.repositories.stream().findFirst().map(Repository::getAll).orElse(Collections.emptyList());
    }

    @Override
    public Optional<Ride> getById(String objectId) {
        return this.repositories.stream().findFirst().flatMap(r -> r.getObjectByID(objectId));
    }

    @Override
    public <R extends Repository<Ride, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
