package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.P2d;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.services.observers.InputObserver;
import sap.ass01.layered.services.dto.EBikeDTO;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class EBikeService implements Service<EBike, String>, InputObserver<EBikeDTO> {

    private final List<Repository<EBike, String>> repositories;

    public EBikeService() {
        this.repositories = new ArrayList<>();
    }

    @Override
    public void notifyUpdateRequested(final EBikeDTO newValue) {
        this.add(this.fromDTO(newValue));
    }

    private EBike fromDTO(final EBikeDTO dto) {
       final var bike = new EBikeImpl(dto.id());
       bike.updateLocation(new P2d(Double.parseDouble(dto.xCoord()), Double.parseDouble(dto.yCoord())));
       return bike;
    }

    @Override
    public void add(final EBike newValue) {
        this.repositories.forEach(r -> r.save(newValue));
    }

    @Override
    public void update(final EBike updatedValue) {
        this.repositories.forEach(r -> r.update(updatedValue));
    }

    @Override
    public Iterable<EBike> getAll() {
        return this.repositories.stream().findFirst().map(Repository::getAll).orElse(Collections.emptyList());
    }

    @Override
    public Optional<EBike> getById(final String objectId) {
        return this.repositories.stream().findFirst().flatMap(r -> r.getObjectByID(objectId));
    }

    @Override
    public <R extends Repository<EBike, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
