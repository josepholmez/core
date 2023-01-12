package com.olmez.core.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

import com.olmez.core.utility.DateTimeUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Temp {

    public static void main(String[] args) {

        int[] numbers = { 5, 2, 5, 2, 3, 4, 3, 3, 3, 2 };

        int[] placedArray = placeToRangeArray(numbers, 0, 5);

        // look at each index/box
        for (int i = 0; i < placedArray.length; i++) {
            // get each numbers in the box
            for (int j = 0; j < placedArray[i]; j++) {
                System.out.println("Sorted list: " + i);
            }
        }

    }

    //////////////////////////////////
    public static int[] placeToRangeArray(int[] numbers, int min, int max) {
        int range = max - min + 1;

        // create an array then put each number to the index same as its value. e.g.
        // first number is 5, so put 5 to the array[5]. In this example:
        // indexArray: 1,2,3,4,5 (5 boxes) after placed numbers to the array: box1 has
        // no number, box2 has three 2, box3 has fourth 3, box4 has one 4, and box5 has
        // two 5.
        int[] indexArray = new int[range];
        for (int num : numbers) {
            indexArray[num]++;
        }
        return indexArray;
    }

}
