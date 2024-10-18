package sap.ass01.layered.services.dto;

public record UserDTO(String id) implements DTO {
    @Override
    public String getId() {
        return id;
    }
}
