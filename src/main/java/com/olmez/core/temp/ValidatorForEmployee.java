package com.olmez.core.temp;

import java.util.function.Function;

import com.olmez.core.model.Employee;

public interface ValidatorForEmployee extends Function<Employee, ValidatorForEmployee.ValidationEnum> {

    enum ValidationEnum {
        SUCCESS,
        NAME_NOT_VALID,
        EMAIL_NOT_VALID,
        PHONE_NOT_VALID,
        AGE_NOT_VALID,
        HIRED_NOT_VALID;

    }

    static ValidatorForEmployee isEmailValid() {
        return emp -> emp.getEmail().contains("@") ? ValidationEnum.SUCCESS
                : ValidationEnum.EMAIL_NOT_VALID;
    }

    static ValidatorForEmployee isNameValid() {
        return emp -> emp.getName().length() > 3 ? ValidationEnum.SUCCESS
                : ValidationEnum.NAME_NOT_VALID;
    }

    static ValidatorForEmployee isHired() {
        return emp -> emp.getDob() != null ? ValidationEnum.SUCCESS
                : ValidationEnum.HIRED_NOT_VALID;
    }

    default ValidatorForEmployee and(ValidatorForEmployee other) {
        return emp -> {
            ValidationEnum result = this.apply(emp);
            return result.equals(ValidationEnum.SUCCESS) ? other.apply(emp) : result;
        };
    }

}
