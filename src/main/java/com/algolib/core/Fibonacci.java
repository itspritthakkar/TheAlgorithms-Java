package com.practice.core;

import com.practice.utils.helpers.MatrixHelper;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Fibonacci {

    public long fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long secondLastSum = 0, lastSum = 1;
        for (int i = 2; i <= n; i++) {
            long temp = secondLastSum + lastSum;
            secondLastSum = lastSum;
            lastSum = temp;
        }
        return lastSum;
    }

    // Not accurate after n = 70
    public long fibonacciFormula(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;

        double fib = (Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5;
        return Math.round(fib); // Round to correct for floating point inaccuracies
    }

    public BigInteger fibonacciMatrixExp(int n) {
        if (n == 0) return ZERO;
        if (n == 1) return ONE;

        BigInteger[][] result = {{ONE, ZERO}, {ZERO, ONE}}; // Identity matrix
        BigInteger[][] base = {{ONE, ONE}, {ONE, ZERO}};

        n = n - 1; // because F(1) = 1, and our base matrix already covers F(1)

        while (n > 0) {
            if (n % 2 == 1)
                result = MatrixHelper.multiplyMatrix(result, base);
            base = MatrixHelper.multiplyMatrix(base, base);
            n /= 2;
        }

        return result[0][0];
    }
}
