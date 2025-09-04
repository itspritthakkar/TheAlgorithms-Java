package com.algolib;

public class Main {
    public static void main(String[] args) {
        String welcomeMessage = """
                ===========================================
                     Welcome to AlgoLab! 🚀
                ===========================================
                
                This project contains implementations of data structures,
                algorithms, and their demos/tests.
                
                👉 To explore the functionality, run the test suite:
                   - Using Maven:
                       mvn test
                
                   - Using an IDE (IntelliJ, Eclipse, VS Code):
                       Navigate to src/test/java and run any test class,
                       e.g., SinglyLinkedListTest, SortingTest, FibonacciTest.
                
                Each demo class has been converted into a proper JUnit test.
                Running them will showcase the algorithms in action ✔
                """;

        System.out.println(welcomeMessage);
    }
}