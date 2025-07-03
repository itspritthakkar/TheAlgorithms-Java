package com.practice.utils.helpers;

import java.math.BigInteger;

public class MatrixHelper {
    public static BigInteger[][] multiplyMatrix(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] c = new BigInteger[2][2];
        c[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
        c[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
        c[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
        c[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
        return c;
    }

    public <T> void printMatrix(T[][] matrix) {
        // Iterate through rows
        for (T[] ts : matrix) {
            // Iterate through columns
            for (T t : ts) {
                System.out.print(t + " "); // Print element with space
            }
            System.out.println(); // Move to the next line after each row
        }
        System.out.println();
    }
}
