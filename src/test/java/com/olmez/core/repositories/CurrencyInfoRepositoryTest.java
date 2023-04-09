package com.olmez.core.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.olmez.core.CoreTestApplication;
import com.olmez.core.model.CurrencyInfo;
import com.olmez.core.utility.TestUtility;

@SpringBootTest(classes = CoreTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class CurrencyInfoRepositoryTest {

	@Autowired
	private CurrencyInfoRepository repository;

	@AfterEach
	void clean() {
		repository.deleteAll();
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
