package com.practice.demos;

import com.practice.core.Sorting;
import com.practice.utils.helpers.ArrayHelper;

import java.util.function.Function;

public class SortingDemo implements Demoable {
    public Integer[] unsortedArray;
    private final Sorting sortingCore = new Sorting();

    @Override
    public void start() {
        System.out.println("================= Sorting demo =================");

        this.unsortedArray = ArrayHelper.generateRandomArray(10, 1, 15);

        System.out.println("Original array:");
        ArrayHelper.printArray(unsortedArray);

//        runSort("bubble sort", sortingCore::bubbleSort);
//        runSort("selection sort", sortingCore::selectionSort);
//        runSort("insertion sort", sortingCore::insertionSort);
//        runSort("merge sort", sortingCore::mergeSort);
        runSort("quick sort", sortingCore::quickSort);
    }

    private void runSort(String sortName, Function<Integer[], Integer[]> sortFunction) {
        Integer[] arrayToSort = unsortedArray.clone();
        Integer[] sortedArray = sortFunction.apply(arrayToSort);

        System.out.printf("Sorted array using %s:%n", sortName);
        ArrayHelper.printArray(sortedArray);
    }
}
