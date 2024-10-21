package sap.ass01.layered.business;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import sap.ass01.layered.persistence.Key;

public interface User extends  Business<User, String> {
    String getId();
    int getCredit();

    void rechargeCredit(int deltaCredit);

    void decreaseCredit(int amount);
}
