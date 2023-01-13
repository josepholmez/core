package com.olmez.core.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SortingUtility {

    /**
     * Counting Sorting
     * <p>
     * First, this creates an array of frequency values of numbers. Then get back
     * the numbers from this array by the number of frequencies.
     * </p>
     * 
     * @param array list of integer
     * @return sorted list
     */
    public static List<Integer> sortInt(int[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        int[] fArray = createFrequencyArray(array);
        return sortFrequencyArray(fArray);
    }

    /**
     * Counting Sorting
     * <p>
     * First, this creates an array of frequency values of numbers. Then get back
     * the numbers from this array by the number of frequencies.
     * </p>
     * 
     * @param array list of integer
     * @return sorted list
     */
    public static List<Integer> sortInt(List<Integer> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        return sortInt(array);
    }

    /**
     * Find how many of each number (frequency). Place frequency values in the
     * relevant index in the new array. For example, if you have nine "number of 5",
     * it will be showing in the array like frequency[5]=9.
     * 
     * @param array numbers
     * @return frequency list
     */
    private int[] createFrequencyArray(int[] array) {
        int size = getMax(array) + 1;
        int[] frequencyArray = new int[size];
        for (int num : array) {
            frequencyArray[num]++;
        }
        return frequencyArray;
    }

    private List<Integer> sortFrequencyArray(int[] frArray) {
        List<Integer> sortedList = new ArrayList<>();
        // The index (i) is also the value of the number in the list.
        for (int i = 0; i < frArray.length; i++) {
            for (int j = 0; j < frArray[i]; j++) {
                sortedList.add(i);
            }
        }
        return sortedList;
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
