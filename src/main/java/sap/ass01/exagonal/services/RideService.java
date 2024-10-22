package sap.ass01.exagonal.services;

import sap.ass01.exagonal.business.*;
import sap.ass01.exagonal.services.dto.RideDTO;
import sap.ass01.exagonal.services.observers.StopSimulationObserver;

import java.util.*;

public class RideService extends AbstractObserverService<RideDTO, Ride> implements StopSimulationObserver {

    private final UserService userService;
    private final EBikeService eBikeService;
    private final HashMap<String, RideSimulation> ridez;

    public RideService(final UserService userService, final EBikeService eBikeService) {
        super();
        this.userService = userService;
        this.eBikeService = eBikeService;
        this.ridez = new HashMap<>();
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        final Optional<User> user = this.userService.getById(newValue.userId());
        final Optional<EBike> eBike = this.eBikeService.getById(newValue.bikeId());
        final List<Ride> rides = new ArrayList<>();
        if (user.isPresent() && eBike.isPresent()) {
            final User realUser = user.get();
            final EBike realEbike = eBike.get();
            if (realEbike.isAvailable() && realEbike.getBatteryLevel() > 0 && realUser.getCredit() > 0) {
                this.repositories.forEach(repo -> {
                    final String id = repo.generateNewId();
                    final Ride newRide = new RideImpl(id, user.get(), eBike.get());
                    rides.add(newRide);
                    this.add(newRide);
                });
                final Ride roba = rides.stream().findFirst().orElseThrow();
                newValue.setId(roba.getId());
                this.startRide(roba);
            }
        }
    }

    private void startRide(final Ride ride) {
        ride.getEBike().updateState(EBike.EBikeState.IN_USE);
        final var sim = ride.start(List.of(this, userService, eBikeService));
        this.ridez.put(ride.getId(), sim);
    }

    @Override
    public void notifyStopSimulation(final String rideId) {
        this.ridez.get(rideId).stopSimulation();
    }
}
