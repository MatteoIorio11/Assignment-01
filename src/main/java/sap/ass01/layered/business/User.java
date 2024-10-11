package sap.ass01.layered.business;

import sap.ass01.layered.persistence.Repository;

public class User implements java.io.Serializable{

	private String id;
	private int credit;
	public User(String id) {
		this.id = id;
		this.credit = 0;
	}


	public String getId() {
		return id;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void rechargeCredit(int deltaCredit) {
		credit += deltaCredit;
	}
	
	public void decreaseCredit(int amount) {
		credit -= amount;
		if (credit < 0) {
			credit = 0;
		}
		// TODO: attach at runtime a new persistence logic
		// NOTE: Call persistence layer about saving the new value
	}
	
	public String toString() {
		return "{ id: " + id + ", credit: " + credit + " }";
	}
}
