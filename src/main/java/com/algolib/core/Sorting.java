package com.algolib.core;

import com.algolib.utils.helpers.ArrayHelper;

public class Sorting {

    public Integer[] bubbleSort(Integer[] arr) {
        boolean swapped;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped in inner loop, array is sorted
            if (!swapped) break;
        }

        return arr;
    }

    public Integer[] selectionSort(Integer[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Assume the minimum is the current index
            int minIndex = i;

            // Find the actual minimum in the rest of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum with the current element
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

        return arr;
    }

    public Integer[] insertionSort(Integer[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements that are greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        return arr;
    }

    public Integer[] mergeSort(Integer[] arr) {
        if (arr.length <= 1) {
            return arr; // already sorted
        }

        // Split array into two halves
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        // Recursively sort each half
        left = mergeSort(left);
        right = mergeSort(right);

        // Merge the sorted halves
        return merge(left, right);
    }

    private Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length + right.length];
        int i = 0, j = 0, k = 0;

        // Compare elements and merge in sorted order
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Add remaining elements (if any)
        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public Integer[] quickSort(Integer[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
        return arr;
    }

    private void quickSortHelper(Integer[] arr, int low, int high) {
        if (low < high) {
            // Partition the array, get the pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort left and right parts
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    private int partition(Integer[] arr, int low, int high) {
        // Step 1
        // Choose random pivot and swap with high
        int pivotIndex = low + (int) (Math.random() * (high - low + 1));
        ArrayHelper.swapElements(arr, pivotIndex, high);

        int pivot = arr[high];  // choose the last element as pivot
        int lastSwapPointer = low;         // index of smaller element

        // Step 2
        // Partition logic
        for (int currentPointer = low; currentPointer < high; currentPointer++) {
            if (arr[currentPointer] < pivot) {
                // Swap arr[lastSwapPointer] and arr[currentPointer]
                ArrayHelper.swapElements(arr, lastSwapPointer, currentPointer);
                lastSwapPointer++;
            }
        }

        // Step 3
        // Place the pivot in the correct position
        ArrayHelper.swapElements(arr, lastSwapPointer, high);

        return lastSwapPointer;  // return the pivot index
    }
}
