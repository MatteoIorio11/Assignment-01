package sap.ass01.plugin;

import sap.ass01.exagonal.business.Ride;
import sap.ass01.exagonal.business.RidePlugin;

public class A implements RidePlugin {
    int c = 0;
    @Override
    public void applyEffect(Ride ride) {
        ride.getUser().decreaseCredit(c);
        c++;
    }
}
