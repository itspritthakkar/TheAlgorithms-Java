package com.algolib;

import com.algolib.core.Sorting;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortingTest {

    private final Sorting sortingCore = new Sorting();

    @Test
    void bubbleSortDemo() {
        Integer[] arr = {5, 3, 8, 1, 2};
        Integer[] sorted = sortingCore.bubbleSort(arr.clone());

        // expected sorted: 1,2,3,5,8
        assertThat(sorted).containsExactly(1, 2, 3, 5, 8);
    }

    @Test
    void selectionSortDemo() {
        Integer[] arr = {9, 7, 5, 3, 1};
        Integer[] sorted = sortingCore.selectionSort(arr.clone());

        // expected sorted: 1,3,5,7,9
        assertThat(sorted).containsExactly(1, 3, 5, 7, 9);
    }

    @Test
    void insertionSortDemo() {
        Integer[] arr = {10, 2, 8, 6, 4};
        Integer[] sorted = sortingCore.insertionSort(arr.clone());

        // expected sorted: 2,4,6,8,10
        assertThat(sorted).containsExactly(2, 4, 6, 8, 10);
    }

    @Test
    void mergeSortDemo() {
        Integer[] arr = {12, 11, 13, 5, 6, 7};
        Integer[] sorted = sortingCore.mergeSort(arr.clone());

        // expected sorted: 5,6,7,11,12,13
        assertThat(sorted).containsExactly(5, 6, 7, 11, 12, 13);
    }

    @Test
    void quickSortDemo() {
        Integer[] arr = {3, 7, 8, 5, 2, 1, 9, 5, 4};
        Integer[] sorted = sortingCore.quickSort(arr.clone());

        // expected sorted: 1,2,3,4,5,5,7,8,9
        assertThat(sorted).containsExactly(1, 2, 3, 4, 5, 5, 7, 8, 9);
    }
}