package com.practice.demos;

import com.practice.core.BinaryExp;

import java.util.Arrays;
import java.util.Map;

public class BinaryExpDemo implements Demoable {
    @Override
    public void start() {
        System.out.println("================= Miscellaneous demo =================");

        permutationBinaryExp();
    }

    private void permutationBinaryExp() {
        BinaryExp m2 = new BinaryExp();

        String[] input = {"cat", "dog", "cow", "mouse", "rhino", "rabbit"};
        int n = 11;

        Map<Integer, Integer> transformation = Map.of(
                1, 2,
                2, 6,
                3, 4,
                4, 5,
                5, 3,
                6, 1
        );

        String[] result = m2.transformN(input, n, transformation);
        System.out.println("After " + n + " transformations: " + Arrays.toString(result));
    }
}
