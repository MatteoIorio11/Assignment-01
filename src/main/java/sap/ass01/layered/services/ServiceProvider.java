package sap.ass01.layered.services;

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

    public UserService getUserService() {
        if (Objects.isNull(this.userService)) {
            this.userService = new UserService();
        }
        return this.userService;
    }

    public RideService getRideService() {
        if (Objects.isNull(this.rideService)) {
            this.rideService = new RideService(
                    this.getUserService().repositories.getFirst(),
                    this.geteBikeService().repositories.getFirst()
            );
        }
        return  this.rideService;
    }
}
