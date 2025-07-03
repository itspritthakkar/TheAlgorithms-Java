package com.practice;

import com.practice.demos.Demoable;
import com.practice.demos.DoublyLinkedListDemo;
import com.practice.demos.FibonacciDemo;
import com.practice.demos.BinaryExpDemo;
import com.practice.demos.SinglyLinkedListDemo;
import com.practice.demos.SortingDemo;

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