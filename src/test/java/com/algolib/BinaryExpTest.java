package com.algolib;

import com.algolib.core.BinaryExp;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryExpTest {

    private final BinaryExp binaryExp = new BinaryExp();
    private final String[] input = {"cat", "dog", "cow", "mouse", "rhino", "rabbit"};
    private final int n = 11;
    private final Map<Integer, Integer> transformation = Map.of(
            1, 2,
            2, 6,
            3, 4,
            4, 5,
            5, 3,
            6, 1
    );

    @Test
    void permutationBinaryExp() {
        String[] result = binaryExp.transformN(input, n, transformation);

        // verify transformation produces expected permutation
        assertThat(result).containsExactly("dog", "rabbit", "mouse", "rhino", "cow", "cat");
    }
}