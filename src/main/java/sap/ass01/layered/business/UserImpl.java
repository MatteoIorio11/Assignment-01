package sap.ass01.layered.business;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import sap.ass01.layered.persistence.Key;

import java.util.Objects;

public class UserImpl implements User {
	@JsonProperty("id")  // Ensure the 'id' field is serialized
	private final String id;

	@JsonProperty("credit")  // Ensure the 'credit' field is serialized
	private int credit;	public UserImpl(String id) {
		this(id, 0);
	}
	@JsonCreator()
	public UserImpl(@JsonProperty("id") String id, @JsonProperty("credit") int credit) {
		this.id = id;
		this.credit = credit;
	}

	@Key
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserImpl user = (UserImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, credit);
	}

	public String toString() {
		return "User: " + this.getId();
	}
}
