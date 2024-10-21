package sap.ass01.layered.services;

import sap.ass01.layered.business.*;
import sap.ass01.layered.services.dto.RideDTO;
import sap.ass01.layered.services.observers.StopSimulationObserver;

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
        final Optional<EBike> eBike =this.eBikeService.getById(newValue.bikeId());
        final List<Ride> rides = new ArrayList<>();
        if (user.isPresent() && eBike.isPresent()) {
            this.eBikeService.getRepositories().forEach(rep -> eBike.get().injectRepository(rep));
            this.userService.getRepositories().forEach(rep -> user.get().injectRepository(rep));

            this.repositories.forEach(repo -> {
                final String id = repo.generateNewId();
                final Ride newRide = new RideImpl(id, user.get(), eBike.get(), this.repositories);
                rides.add(newRide);
                this.add(newRide);
            });

            // TODO: quite shitty
            final Ride roba = rides.stream().findFirst().orElseThrow();
            newValue.setId(roba.getId());
            this.startRide(roba);
        }
    }

    private void startRide(final Ride ride) {
        // TODO: also quite shitty, a list of stuff then later filtered with `filterIsInstance` :\
        ride.getEBike().updateState(EBike.EBikeState.IN_USE);
        final var sim = ride.start(List.of(this, userService, eBikeService));
        this.ridez.put(ride.getId(), sim);
    }

    @Override
    public void notifyStopSimulation(final String rideId) {
        this.ridez.get(rideId).stopSimulation();
    }
}
