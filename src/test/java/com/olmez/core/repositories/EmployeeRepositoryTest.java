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
import com.olmez.core.model.Employee;
import com.olmez.core.utility.TestUtility;

@SpringBootTest(classes = CoreTestApplication.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(TestUtility.TEST_PROFILE)
@TestPropertySource(TestUtility.TEST_SOURCE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@AfterEach
	void clean() {
		repository.deleteAll();
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
