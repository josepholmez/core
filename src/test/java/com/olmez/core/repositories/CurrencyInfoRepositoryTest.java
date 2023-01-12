package com.olmez.core.repositories;

import com.olmez.core.CoreApplicationTest;
import com.olmez.core.model.CurrencyInfo;
import com.olmez.core.services.TestRepoCleanerService;
import com.olmez.core.utility.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = CoreApplicationTest.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
class CurrencyInfoRepositoryTest {

	@Autowired
	private CurrencyInfoRepository repository;
	@Autowired
	private TestRepoCleanerService cleanerService;

	@BeforeEach
	public void setup() {
		cleanerService.clear();
	}

	@Test
	void testFindAll() {
		// arrange
		var date = LocalDate.of(2022, 12, 3);
		var info = new CurrencyInfo(date);
		info = repository.save(info);

		var info2 = new CurrencyInfo(LocalDate.of(2022, 12, 4));
		info2 = repository.save(info2);

		var info3 = new CurrencyInfo(LocalDate.of(2022, 12, 2));
		info3 = repository.save(info3);

		// act
		var infos = repository.findAll();

		// assert
		assertThat(infos).hasSize(3);
		assertThat(infos.get(0)).isEqualTo(info2); // Dec 4
		assertThat(infos.get(1)).isEqualTo(info); // Dec 3
		assertThat(infos.get(2)).isEqualTo(info3); // Dec 2
		assertThat(infos.get(2)).isEqualTo(info3); // Dec 2

	}

}
