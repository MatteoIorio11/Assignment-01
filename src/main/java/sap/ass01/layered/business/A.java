package sap.ass01.layered.business;

public class A implements RidePlugin{
    int c = 0;
    @Override
    public void applyEffect(Ride ride) {
        ride.getUser().decreaseCredit(c);
        c++;
    }
}
