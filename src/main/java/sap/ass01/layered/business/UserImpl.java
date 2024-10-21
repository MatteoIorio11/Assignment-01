package sap.ass01.layered.business;

import com.fasterxml.jackson.annotation.*;
import sap.ass01.layered.persistence.Key;
import sap.ass01.layered.persistence.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties like "available"
public class UserImpl implements User, Business<User, String> {
	@JsonProperty("id")  // Ensure the 'id' field is serialized
	private final String id;

	@JsonProperty("credit")  // Ensure the 'credit' field is serialized
	private int credit;
	@JsonIgnore
	private List<Repository<User, String>> repositories = new LinkedList<>();
	public UserImpl(String id) {
		this(id, 100);
	}
	public UserImpl(String id, final List<Repository<User, String>> repositories) {
		this(id);
		this.repositories = repositories;
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

	@Override
	public void injectRepository(Repository<User, String> repository) {
		this.repositories.add(repository);
	}
	@Override
	public void save() {
		this.repositories.forEach(repo -> repo.save(this));
	}

	@Override
	public void update() {
		this.repositories.forEach(repo -> repo.update(this));
	}
}
