package com.olmez.core.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee extends BaseObject {

	private String name;
	private String email;
	private Integer age;
	private String phone;
	private LocalDate hiredOn;

	public Employee(String name, String email) {
		this(name, email, null, null, null);
	}

	public Employee(String name, String email, Integer age, String phone, LocalDate hiredOn) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.hiredOn = hiredOn;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email);
	}

}
