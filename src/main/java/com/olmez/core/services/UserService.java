package com.olmez.core.services;

import java.util.List;

import com.olmez.core.model.PasswordWrapper;
import com.olmez.core.model.User;

public interface UserService {

    List<User> getUsers();

    boolean addUser(User user);

    User getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long existingUserId, User givenUser);

    User getUserByUsername(String username);

    User updateUser(User givenUser);

    User getCurrentUser();

    boolean updateUserPassword(PasswordWrapper passWrapper);
}
