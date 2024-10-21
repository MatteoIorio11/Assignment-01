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
        final EBike eBike = this.fromDTO(newValue);
        if (this.getById(eBike.getId()).isEmpty()) {
            this.add(eBike);
        }else{
            System.err.println("Ebike ID<<" + eBike.getId() + ">>, already exists.");
        }
    }

    private EBike fromDTO(final EBikeDTO dto) {
       final var bike = new EBikeImpl(dto.id(), this.getRepositories());
       bike.updateLocation(new P2d(Double.parseDouble(dto.xCoord()), Double.parseDouble(dto.yCoord())));
       return bike;
    }
}
