package com.algolib.demos.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackArray;
import com.algolib.demos.Demoable;
import com.algolib.utils.helpers.ArrayHelper;

import java.util.Random;

public class StackArrayDemo implements Demoable {

    Random rand = new Random();

    @Override
    public void start() {
        System.out.println("================= Stack Array demo =================");

        crudDemo();
        reverseDemo();
    }

    private void crudDemo() {
        try {
            Stack<Integer> sll = new StackArray<>(ArrayHelper.generateRandomArray(10, 1, 20));

            Integer rand1 = rand.nextInt(100);
            sll.push(rand1);

            Integer lastInsertedPeek = sll.peek();

            Integer lastInsertedPop = sll.pop();

            if (!lastInsertedPeek.equals(lastInsertedPop) || !lastInsertedPeek.equals(rand1) || sll.contains(rand1)) {
                System.out.printf("Peek and Pop demo failed. Last inserted data Peek: %d. Pop: %d%n", lastInsertedPeek, lastInsertedPop);
            } else {
                System.out.printf("Peek: %d. Pop: %d%n", lastInsertedPeek, lastInsertedPop);
            }

            Integer rand2 = rand.nextInt(100);

            sll.push(rand2);

            int foundIndex = sll.search(rand2);

            if (foundIndex < 0) {
                System.out.println("Search failed");
            } else {
                System.out.println("Search working");
            }

            sll.clear();
        } catch(IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reverseDemo() {
        Stack<Integer> sll = new StackArray<>(ArrayHelper.generateRandomArray(10, 1, 20));
        Stack<Integer> sllOriginal = sll.copy();
        sll.reverse();

        Stack<Integer> sllCopy = sllOriginal.reverseCopy();

        if (!sll.equals(sllCopy)) {
            System.out.println("Reverse failed");
        } else {
            System.out.println("Reverse works");
        }
    }
}
