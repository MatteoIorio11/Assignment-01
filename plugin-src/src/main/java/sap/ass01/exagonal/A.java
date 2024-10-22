package sap.ass01.plugin;

import sap.ass01.plugin.Ride;
import sap.ass01.plugin.RidePlugin;

public class A implements RidePlugin {
    int c = 0;
    @Override
    public void applyEffect(Ride ride) {
        ride.getUser().decreaseCredit(c);
        c++;
    }
}
