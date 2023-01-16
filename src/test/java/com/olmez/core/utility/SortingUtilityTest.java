package com.olmez.core.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SortingUtilityTest {

    private int arr[] = { 10, 9, 1, 5, 7, 2, 6, 8, 4, 3 };
    private int sorted[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    private int emptyArray[] = {};

    @Test
    void testCountSort() {
        // for null value
        assertThat(SortingUtility.countSort(null)).isNull();

        // for empty array
        assertThat(SortingUtility.countSort(emptyArray)).isEmpty();

        var result = SortingUtility.countSort(arr);
        assertThat(result).isEqualTo(sorted);
    }

    @Test
    void testQuickSort() {
        // for null value
        assertThat(SortingUtility.quickSort(null)).isNull();

        // for empty array
        assertThat(SortingUtility.quickSort(emptyArray)).isEmpty();

        var result = SortingUtility.quickSort(arr);
        assertThat(result).isEqualTo(sorted);
    }

    @Test
    void testMergeSort() {
        // for null value
        assertThat(SortingUtility.mergeSort(null)).isNull();

        // for empty array
        assertThat(SortingUtility.mergeSort(emptyArray)).isEmpty();

        var result = SortingUtility.mergeSort(arr);
        assertThat(result).isEqualTo(sorted);
    }

}
