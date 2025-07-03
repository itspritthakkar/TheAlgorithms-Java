package com.algolib;

import com.algolib.demos.Demoable;
import com.algolib.demos.DoublyLinkedListDemo;
import com.algolib.demos.FibonacciDemo;
import com.algolib.demos.BinaryExpDemo;
import com.algolib.demos.SinglyLinkedListDemo;
import com.algolib.demos.SortingDemo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the algorithms!");

        // Index of all demos to run
        List<Demoable> demos = List.of(
                new SinglyLinkedListDemo(),
                new DoublyLinkedListDemo(),
                new BinaryExpDemo(),
                new FibonacciDemo(),
                new SortingDemo()
        );

        demos.forEach(Demoable::start);
    }
}