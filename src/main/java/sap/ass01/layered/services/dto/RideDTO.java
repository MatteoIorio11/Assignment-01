package sap.ass01.layered.services.dto;

public record RideDTO(
        String bikeId,
        String userId
) implements DTO{
    @Override
    public Object getId() {
        return null;
    }
}
