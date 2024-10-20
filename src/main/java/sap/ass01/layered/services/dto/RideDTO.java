package sap.ass01.layered.services.dto;

import java.util.Optional;

public class RideDTO implements DTO{

    private Optional<String> id;
    private final String bikeId;
    private final String userId;

    public RideDTO(final Optional<String> id, final String bikeId, final String userId) {
        this.id = id;
        this.bikeId = bikeId;
        this.userId = userId;
    }

    public RideDTO(final String bikeId, final String userId) {
        this(Optional.empty(), bikeId, userId);
    }

    @Override
    public Optional<String >getId() {
        return this.id;
    }

    public String bikeId() {
        return bikeId;
    }

    public String userId() {
        return this.userId;
    }

    public void setId(final String rideId) {
        this.id = Optional.of(rideId);
    }
}
