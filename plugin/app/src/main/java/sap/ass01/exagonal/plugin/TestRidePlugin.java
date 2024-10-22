package sap.ass01.exagonal.plugin;

import sap.ass01.exagonal.business.Ride;

public class TestRidePlugin implements RidePlugin {

    private int c = 0;

    @Override
    public void applyEffect(final Ride ride) {
        ride.getUser().decreaseCredit(this.c);
        this.c++;
    }
    
}
