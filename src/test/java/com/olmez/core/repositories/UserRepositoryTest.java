package com.olmez.core.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
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

    private User user = new User("First", "Last", "uname", "email");
    private User user2 = new User("First2", "Last2", "uname2", "email2");

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void testFindAll() {
        // act
        var list = repository.findAll();
        // assert
        assertThat(list).isEmpty();
    }

    @Test
    void testFindByUsername() {
        // arrange
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
        user = repository.save(user);
        user2 = repository.save(user2);
        // act
        var resUser = repository.findByUsername(user.getUsername());
        // assert
        assertThat(user).isNotNull().isEqualTo(resUser);
    }

}
