package com.algolib.demos;

import com.algolib.core.Fibonacci;

import java.math.BigInteger;

public class FibonacciDemo implements Demoable{
    @Override
    public void start() {
        System.out.println("================= Fibonacci demo =================");

        fibonacciDemo();
    }

    private void fibonacciDemo() {
        Fibonacci f1 = new Fibonacci();

        int fibonacciInput = 15;
        long fibonacciOutput = f1.fibonacci(fibonacciInput);
        long fibonacciFormulaOutput = f1.fibonacciFormula(fibonacciInput);
        BigInteger fibonacciMatrixExpOutput = f1.fibonacciMatrixExp(fibonacciInput);

        System.out.printf("Fibonacci series sum for %d is: %d%n", fibonacciInput, fibonacciOutput);
        System.out.printf("Fibonacci series sum for %d using formula is: %d%n", fibonacciInput, fibonacciFormulaOutput);
        System.out.printf("Fibonacci series sum for %d using Matrix Exponentiation is: %d%n", fibonacciInput, fibonacciMatrixExpOutput);
    }
}
