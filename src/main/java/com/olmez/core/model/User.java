package com.olmez.core.model;

import java.util.Objects;

import com.olmez.core.model.enums.UserType;
import com.olmez.core.utility.DateTimeUtility;
import com.olmez.core.utility.StringUtility;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Required to solve the conflict "user" in h2 db
@Data
@NoArgsConstructor
public class User extends BaseObject {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private UserType userType = UserType.REGULAR;
	private String passwordHash;
	private String timeZone;

	public User(String username, String firstName, String lastName) {
		this(username, firstName, lastName, username + "@no-email.com", UserType.REGULAR);
	}

	public User(String username, String firstName, String lastName, String email) {
		this(username, firstName, lastName, email, UserType.REGULAR);
	}

	public User(String username, String firstName, String lastName, String email, UserType userType) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.email = email;
	}

	public String getName() {
		return StringUtility.isEmpty(firstName) ? username : firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return getName();
	}

	public boolean isAdmin() {
		return userType == UserType.ADMIN;
	}

	public String getRole() {
		return userType.getRole();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, email);
	}

	public String getTimeZone() {
		return (timeZone == null) ? DateTimeUtility.DEFAULT_ZONE_ID_KEY : timeZone;
	}

}
