package sap.ass01.exagonal.services.dto;

public record EBikeDTO(
        String id,
        String xCoord,
        String yCoord
) implements DTO {
    @Override
    public Object getId() {
        return id;
    }
}
