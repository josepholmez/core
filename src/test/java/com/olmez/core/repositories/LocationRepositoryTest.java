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
import com.olmez.core.model.Location;
import com.olmez.core.utility.TestUtility;

@SpringBootTest(classes = CoreTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class LocationRepositoryTest {

	@Autowired
	private LocationRepository repository;

	@AfterEach
	void clean() {
		repository.deleteAll();
	}

	@Test
	void testFindByName() {
		// arrange
		var location = new Location("Test location", 45., -80.);
		location = repository.save(location);
		var location2 = new Location("Test location2", 46., -81.);
		location2 = repository.save(location2);

		// act
		var locations = repository.findByName(location.getName());

		// assert
		assertThat(locations).hasSize(1);
		assertThat(locations.get(0)).isEqualTo(location);
	}

	@Test
	void testFindByLatAndLong() {
		// arrange
		var location = new Location("Test location", 45., -80.);
		location = repository.save(location);
		var location2 = new Location("Test location2", 46., -81.);
		location2 = repository.save(location2);

		// act
		var locations = repository.findByLatAndLong(45., -80.);

		// assert
		assertThat(locations).hasSize(1);
		assertThat(locations.get(0)).isEqualTo(location);
	}

	@Test
	void testFindByLatAndLongRange() {
		// arrange
		var location = new Location("Test location", 45., -80.);
		location = repository.save(location);
		var location2 = new Location("Test location2", 46., -81.);
		location2 = repository.save(location2);
		var location3 = new Location("Test location3", 49., -84.);
		location3 = repository.save(location3);
		var location4 = new Location("Test location4", 50., -85.);
		location4 = repository.save(location4);

		// act
		var locations = repository.findByLatAndLongRange(45., 50., -85., -80.);
		// assert
		assertThat(locations).hasSize(4);

		// act
		locations = repository.findByLatAndLongRange(45., 50., -80.5, -80.);
		// assert
		assertThat(locations).hasSize(1);
		assertThat(locations.get(0)).isEqualTo(location);

		// act
		locations = repository.findByLatAndLongRange(50., 55., -85., -80.);
		// assert
		assertThat(locations).hasSize(1);
		assertThat(locations.get(0)).isEqualTo(location4);
	}

}
