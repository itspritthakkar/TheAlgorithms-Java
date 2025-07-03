package com.practice.core;

import java.util.Map;

public class BinaryExp {
    // Compose: result = b ∘ a (i.e., apply 'a' then 'b')
    private int[] compose(int[] a, int[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = b[a[i]];
        }
        return result;
    }

    // Binary exponentiation of a permutation
    private int[] permutePower(int[] base, int exponent) {
        int[] result = new int[base.length];
        for (int i = 0; i < base.length; i++) result[i] = i; // Identity

        while (exponent > 0) {
            if ((exponent & 1) == 1)
                result = compose(result, base);
            base = compose(base, base);
            exponent >>= 1;
        }

        return result;
    }

    // Apply a permutation to an array
    private String[] apply(String[] arr, int[] perm) {
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[perm[i]] = arr[i];
        }
        return result;
    }

    public String[] transformN(String[] input, int n, Map<Integer, Integer> map) {
        int len = input.length;

        // Convert 1-based map to 0-based permutation
        int[] perm = new int[len];
        for (int i = 0; i < len; i++) {
            int from = i + 1;
            Integer to = map.get(from);
            if (to == null)
                throw new IllegalArgumentException("Incomplete transformation map for index: " + from);
            perm[i] = to - 1;
        }

        int[] poweredPerm = permutePower(perm, n);
        return apply(input, poweredPerm);
    }
}
