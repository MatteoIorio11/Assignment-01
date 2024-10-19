package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.P2d;
import sap.ass01.layered.persistence.Repository;
import sap.ass01.layered.presentation.observers.InputObserver;
import sap.ass01.layered.services.dto.EBikeDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class EBikeService implements Service<EBike, String>, InputObserver<EBikeDTO> {

    protected final List<Repository<EBike, String>> repositories;

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
    public List<EBike> getAll() {
        // Don't know why I can't do `flatMap(Repository::getAll).distinct().toList()` :(
        final List<EBike> ebikes = new ArrayList<>();
        this.repositories.stream().map(Repository::getAll).forEach(i -> i.forEach(ebikes::add));
        return ebikes.stream().distinct().toList();
    }

    @Override
    public <R extends Repository<EBike, String>> void addRepository(final R repository) {
        this.repositories.add(repository);
    }
}
