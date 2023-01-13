package com.olmez.core.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.olmez.core.utility.SortingUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Temp {

    public static void main(String[] args) {

        int[] array = { 5, 5, 5, 5, 5, 4, 5, 5, 5, 5 };
        List<Integer> li = Arrays.stream(array).boxed().toList();
        System.out.println(SortingUtility.sortInt(li));

        System.out.println("Primitive list: " + li);
    }

}
