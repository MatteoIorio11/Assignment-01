package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.Ride;
import sap.ass01.layered.business.RideImpl;
import sap.ass01.layered.business.User;
import sap.ass01.layered.services.dto.RideDTO;

import java.util.*;

public class RideService extends AbstractObserverService<RideDTO, Ride> {

    private final UserService userService;
    private final EBikeService eBikeService;

    public RideService(final UserService userService, final EBikeService eBikeService) {
        super();
        this.userService = userService;
        this.eBikeService = eBikeService;
    }

    @Override
    public void notifyUpdateRequested(final RideDTO newValue) {
        final Optional<User> user = this.userService.getById(newValue.userId());
        final Optional<EBike> eBike = this.eBikeService.getById(newValue.bikeId());
        final List<Ride> rides = new ArrayList<>();
        if (user.isPresent() && eBike.isPresent()) {
            this.repositories.forEach(repo -> {
                final String id = repo.generateNewId();
                final Ride newRide = new RideImpl(id, user.get(), eBike.get());
                rides.add(newRide);
                this.add(newRide);
            });

            // TODO: quite shitty
            this.startRide(rides.stream().findFirst().orElseThrow());
        }
    }

    private void startRide(final Ride ride) {
        // TODO: also quite shitty, a list of stuff then later filtered with `filterIsInstance` :\
        ride.getEBike().updateState(EBike.EBikeState.IN_USE);
        ride.start(List.of(this, userService, eBikeService));
    }
}
