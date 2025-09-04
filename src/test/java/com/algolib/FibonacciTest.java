package com.algolib;

import com.algolib.core.Fibonacci;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciTest {

    private final int fibonacciInput = 15;
    private final Fibonacci fibonacci = new Fibonacci();

    @Test
    void fibonacciIterative() {
        long result = fibonacci.fibonacci(fibonacciInput);

        // 15th Fibonacci number = 610
        assertThat(result).isEqualTo(610);
    }

    @Test
    void fibonacciFormula() {
        long result = fibonacci.fibonacciFormula(fibonacciInput);

        // 15th Fibonacci number = 610
        assertThat(result).isEqualTo(610);
    }

    @Test
    void fibonacciMatrixExponentiation() {
        BigInteger result = fibonacci.fibonacciMatrixExp(fibonacciInput);

        // 15th Fibonacci number = 610
        assertThat(result).isEqualTo(BigInteger.valueOf(610));
    }
}
