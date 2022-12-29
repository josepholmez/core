package com.olmez.core.mockdata;

import com.olmez.core.model.User;
import com.olmez.core.model.enums.UserType;

public class MockedUser extends User {
	private static long generatedId = 1;

	public MockedUser(String firstName, String lastName) {
		super(firstName + lastName, firstName, lastName, firstName + "@mocked.com", UserType.REGULAR);
		setId(generatedId++);
	}

}
