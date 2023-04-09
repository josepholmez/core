package com.olmez.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.olmez.core.model.User;
import com.olmez.core.model.enums.UserType;
import com.olmez.core.utility.StringUtility;

@Repository
public interface UserRepository extends BaseObjectRepository<User> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.deleted = false")
    List<User> findUsersByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.deleted = false")
    Optional<User> findByEmail(String email);

    default User findByUsername(String username) {
        if (StringUtility.isEmpty(username)) {
            return null;
        }
        List<User> users = findUsersByUsername(username);
        if (users.isEmpty()) {
            return null;
        }
        if (users.size() > 1) {
            // keep latest one
            for (int i = 1; i < users.size(); i++) {
                users.get(i).setDeleted(true);
            }
            saveAll(users);
        }
        return users.get(0);
    }

    default User findUserByEmail(String email) {
        Optional<User> oUser = findByEmail(email);
        return oUser.isPresent() ? oUser.get() : null;
    }

    default User getAppUser() {
        User user = findByUsername("app_user");
        if (user == null) {
            user = new User("app-user", "AppFirst", "AppLast", "app.user@email.com", UserType.APPLICATION);
            user = save(user);
        }
        return user;
    }
}
