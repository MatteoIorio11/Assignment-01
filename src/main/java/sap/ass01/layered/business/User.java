package sap.ass01.layered.business;

public interface User extends java.io.Serializable {
    String getId();

    int getCredit();

    void rechargeCredit(int deltaCredit);

    void decreaseCredit(int amount);
}
