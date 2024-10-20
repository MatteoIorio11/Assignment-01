package sap.ass01.layered.services;

import sap.ass01.layered.business.EBike;
import sap.ass01.layered.business.EBikeImpl;
import sap.ass01.layered.business.P2d;
import sap.ass01.layered.services.dto.EBikeDTO;

public class EBikeService extends AbstractObserverService<EBikeDTO, EBike> {

    public EBikeService() {
        super();
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
}
