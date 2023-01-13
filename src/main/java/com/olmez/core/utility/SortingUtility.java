package com.olmez.core.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SortingUtility {

    /**
     * COUNTING SORTING
     * <p>
     * First, this creates an array of frequency values of numbers. Then get back
     * the numbers from this array by the number of frequencies.
     * </p>
     * 
     * @param array list of integer
     * @return sorted list
     */
    public static List<Integer> countSort(int[] array) {
        if (array == null) {
            return Collections.emptyList();
        }
        int[] fArray = createFrequencyArray(array);
        return sortFrequencyArray(fArray);
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

    /**
     * QUICK SORT
     * <p>
     * This takes as parameters the array to be sorted, the first and the last
     * index. First, it checks the indices and continues only if there are still
     * elements to be sorted. It gets the index of the sorted pivot and uses it to
     * recursively call the partition() method with the same parameters as the
     * quickSort() method, but with different indices. The last element is selected
     * as a pivot arbitrarily. Then, checks each element and swaps it before the
     * pivot if its value is smaller. By the end of the partitioning, all elements
     * less than the pivot are on the left of it, and all elements greater than the
     * pivot is on the right of it. The pivot is at its final sorted position and
     * the function returns this position.
     * <p>
     * 
     * @param arr to be sorted array
     * @return sorted array
     */
    public static int[] quickSort(int[] arr) {
        if (arr.length > 1) {
            quickSort(arr, 0, arr.length - 1);
        }
        return arr;
    }

    private void quickSort(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            int partIndex = partition(arr, beginIndex, endIndex);

            quickSort(arr, beginIndex, partIndex - 1);
            // next comparing
            quickSort(arr, partIndex + 1, endIndex);
        }
    }

    private int partition(int[] arr, int beginIndex, int endIndex) {
        int i = (beginIndex - 1);

        int pivot = arr[endIndex]; // arbitrary selection
        for (int j = beginIndex; j < endIndex; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, endIndex);
        return i + 1;
    }

    // it switches arr[i] to arr[j]
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
