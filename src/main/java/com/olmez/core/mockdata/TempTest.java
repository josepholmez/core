package com.olmez.core.mockdata;

import java.time.LocalDate;
import java.util.function.Consumer;

import com.olmez.core.model.Employee;

public class TempTest {

    public static void main(String[] args) {

        hello("John", null, (firstName) -> System.out.println("No last name provided for " + firstName));

    }

    static void hello(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            callback.accept(firstName);
        }
    }

}
