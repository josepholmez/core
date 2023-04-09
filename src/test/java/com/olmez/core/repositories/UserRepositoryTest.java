package com.olmez.core.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.core.CoreTestApplication;
import com.olmez.core.model.User;
import com.olmez.core.utility.TestUtility;

@SpringBootTest(classes = CoreTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @AfterEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void testFindByUsername() {
        // arrange
        User user = new User("uname", "First", "Last");
        user = repository.save(user);

        // act
        var users = repository.findUsersByUsername(user.getUsername());

        // assert
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
    }

    @Test
    void testGetByUsername() {
        // arrange
        User user1 = new User("uname", "First", "Last");
        user1 = repository.save(user1);
        User user2 = new User("uname2", "First2", "Last2");
        user2 = repository.save(user2);

        // act
        var user = repository.findByUsername(user1.getUsername());

        // assert
        assertThat(user).isNotNull().isEqualTo(user1);
    }

}
