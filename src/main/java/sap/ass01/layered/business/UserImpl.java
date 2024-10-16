package sap.ass01.layered.business;

public class UserImpl implements User {

	private final String id;
	private int credit;
	public UserImpl(String id) {
		this.id = id;
		this.credit = 0;
	}


	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public int getCredit() {
		return credit;
	}
	
	@Override
	public void rechargeCredit(int deltaCredit) {
		credit += deltaCredit;
	}
	
	@Override
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
