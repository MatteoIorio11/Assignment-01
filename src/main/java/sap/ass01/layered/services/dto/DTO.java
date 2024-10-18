package sap.ass01.layered.services.dto;

import java.util.Collection;

public interface DTO {
    Object getId();

    static <D extends DTO> Iterable<D> filterIsInstance(final Collection<DTO> c, Class<D> clazz) {
        return c.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }
}
