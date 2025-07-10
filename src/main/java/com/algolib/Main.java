package com.algolib;

import com.algolib.demos.Demoable;
import com.algolib.demos.lists.DoublyLinkedListDemo;
import com.algolib.demos.FibonacciDemo;
import com.algolib.demos.BinaryExpDemo;
import com.algolib.demos.lists.SinglyLinkedListDemo;
import com.algolib.demos.SortingDemo;
import com.algolib.demos.stacks.StackArrayDemo;
import com.algolib.demos.stacks.StackArrayListDemo;
import com.algolib.demos.stacks.StackLinkedListDemo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to the algolab!");

        // Index of all demos to run
        List<Demoable> demos = List.of(
                new SinglyLinkedListDemo(),
                new DoublyLinkedListDemo(),
                new BinaryExpDemo(),
                new FibonacciDemo(),
                new SortingDemo(),
                new StackLinkedListDemo(),
                new StackArrayDemo(),
                new StackArrayListDemo()
        );

        demos.forEach(Demoable::start);
    }
}