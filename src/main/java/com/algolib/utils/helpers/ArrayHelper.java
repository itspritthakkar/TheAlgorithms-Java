package com.algolib.utils.helpers;

import java.util.Arrays;
import java.util.Random;

public class ArrayHelper {
    public static Integer[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    public static void swapElements(Integer[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static <T> void printArray(T[] array) {
        System.out.println(Arrays.toString(array));
    }
}
