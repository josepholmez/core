package com.olmez.core.mockdata;

import java.util.function.Function;

import com.olmez.core.model.Employee;

public interface Validator extends Function<Employee, ValidationResult> {

    static Validator isEmailValid() {
        return emp -> emp.getEmail().contains("@") ? ValidationResult.SUCCESS : ValidationResult.EMAIL_NOT_VALID;
    }

    static Validator isNameValid() {
        return emp -> emp.getName().length() > 3 ? ValidationResult.SUCCESS : ValidationResult.NAME_NOT_VALID;
    }

    static Validator isPhoneValid() {
        return emp -> emp.getPhone().length() == 10 ? ValidationResult.SUCCESS : ValidationResult.PHONE_NOT_VALID;
    }

    static Validator isAgeValid() {
        return emp -> emp.getAge() > 18 ? ValidationResult.SUCCESS : ValidationResult.AGE_NOT_VALID;
    }

    static Validator isHired() {
        return emp -> emp.getHiredOn() != null ? ValidationResult.SUCCESS : ValidationResult.HIRED_NOT_VALID;
    }

    default Validator and(Validator other) {
        return emp -> {
            ValidationResult result = this.apply(emp);
            return result.equals(ValidationResult.SUCCESS) ? other.apply(emp) : result;
        };
    }

}
