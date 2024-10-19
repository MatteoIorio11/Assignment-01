package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.Ride;
import sap.ass01.layered.business.User;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.persistence.json.Serializer;
import sap.ass01.layered.persistence.repositories.EBikeRepository;
import sap.ass01.layered.persistence.repositories.RideRepository;
import sap.ass01.layered.persistence.repositories.UserRepository;

import java.util.Objects;

public class ServiceProvider {
    private EBikeService eBikeService;
    private UserService userService;
    private RideService rideService;

    public ServiceProvider() {
        this.eBikeService = null;
        this.userService = null;
        this.rideService = null;
    }

    public EBikeService geteBikeService() {
        if (Objects.isNull(this.eBikeService)) {
            this.eBikeService = new EBikeService();
        }
        return this.eBikeService;
    }

    public void setEBikeRepository(final Repository<EBike, String> bikeRepo) {
        this.eBikeService.addRepository(bikeRepo);
    }

    public UserService getUserService() {
        if (Objects.isNull(this.userService)) {
            this.userService = new UserService();
        }
        return this.userService;
    }

    public void setUserRepository(final Repository<User, String> userRepo) {
        this.userService.addRepository(userRepo);
    }

    public RideService getRideService() {
        if (Objects.isNull(this.rideService)) {
            this.rideService = new RideService(this.getUserService(), this.geteBikeService());
        }
        return  this.rideService;
    }

    public void setRideRepository(final Repository<Ride, String> rideRepo) {
        this.rideService.addRepository(rideRepo);
    }

    private void initializeAllNow() {
        this.getRideService();
    }

    public static ServiceProvider getJSONSerializableServiceProvider() {
        final var sp = new ServiceProvider();
        sp.initializeAllNow();
        sp.setUserRepository(new UserRepository(Serializer.userJSONSerializer()));
        sp.setEBikeRepository(new EBikeRepository(Serializer.ebikeJSONSerializer()));
        sp.setRideRepository(new RideRepository(Serializer.rideJSONSerializer()));
        return sp;
    }
}
