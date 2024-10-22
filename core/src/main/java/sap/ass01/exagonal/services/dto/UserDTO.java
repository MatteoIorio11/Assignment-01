package sap.ass01.exagonal.services.dto;

public record UserDTO(String id) implements DTO {
    @Override
    public String getId() {
        return id;
    }
}
