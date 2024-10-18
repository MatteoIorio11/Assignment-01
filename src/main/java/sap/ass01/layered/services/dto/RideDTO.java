package sap.ass01.layered.services.dto;

public record RideDTO(
        String bikeId,
        String userName
) implements DTO{
    @Override
    public Object getId() {
        return null;
    }
}
