package com.olmez.core.temp;

import java.util.function.Function;

import com.olmez.core.model.Employee;

public interface EmployeeRegValidator extends Function<Employee, EmployeeValidationResult> {

    static EmployeeRegValidator isEmailValid() {
        return emp -> emp.getEmail().contains("@") ? EmployeeValidationResult.SUCCESS
                : EmployeeValidationResult.EMAIL_NOT_VALID;
    }

    static EmployeeRegValidator isNameValid() {
        return emp -> emp.getName().length() > 3 ? EmployeeValidationResult.SUCCESS
                : EmployeeValidationResult.NAME_NOT_VALID;
    }

    static EmployeeRegValidator isPhoneValid() {
        return emp -> emp.getPhone().length() == 10 ? EmployeeValidationResult.SUCCESS
                : EmployeeValidationResult.PHONE_NOT_VALID;
    }

    static EmployeeRegValidator isAgeValid() {
        return emp -> emp.getAge() > 18 ? EmployeeValidationResult.SUCCESS : EmployeeValidationResult.AGE_NOT_VALID;
    }

    static EmployeeRegValidator isHired() {
        return emp -> emp.getHiredOn() != null ? EmployeeValidationResult.SUCCESS
                : EmployeeValidationResult.HIRED_NOT_VALID;
    }

    default EmployeeRegValidator and(EmployeeRegValidator other) {
        return emp -> {
            EmployeeValidationResult result = this.apply(emp);
            return result.equals(EmployeeValidationResult.SUCCESS) ? other.apply(emp) : result;
        };
    }

}
