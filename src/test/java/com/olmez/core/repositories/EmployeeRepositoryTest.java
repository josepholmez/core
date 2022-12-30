package com.olmez.core.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.olmez.core.CoreApplicationTest;
import com.olmez.core.model.Employee;
import com.olmez.core.services.TestRepoCleanerService;
import com.olmez.core.utility.TestUtility;

/**
 * Test classes use test database!
 */
@SpringBootTest(classes = CoreApplicationTest.class)
@TestPropertySource(TestUtility.SOURCE_PROPERTIES)
@ActiveProfiles(TestUtility.PROFILE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private TestRepoCleanerService cleanerService;

	@BeforeEach
	public void setup() {
		cleanerService.clear();
	}

	@Test
	void testFindByName() {
		// arrange
		var emp = new Employee("Emp1name", "emp@email.com");
		emp = repository.save(emp);
		var emp2 = new Employee("Emp2name", "emp2@email.com");
		emp2 = repository.save(emp2);

		// act
		var list = repository.findByName(emp.getName());

		// assert
		assertThat(list).hasSize(1);
		assertThat(list.get(0)).isEqualTo(emp);
	}

}
