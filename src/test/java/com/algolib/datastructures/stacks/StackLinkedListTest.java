package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackLinkedList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackLinkedListTest {

    @Test
    void crudDemo() {
        Stack<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3});

        // push
        Integer value = 42;
        sll.push(value);

        // peek
        Integer peeked = sll.peek();
        assertThat(peeked).isEqualTo(value);

        // pop
        Integer popped = sll.pop();
        assertThat(popped).isEqualTo(value);
        assertThat(sll.contains(value)).isFalse();

        // push + search
        Integer value2 = 99;
        sll.push(value2);
        int foundIndex = sll.search(value2);
        assertThat(foundIndex).isGreaterThanOrEqualTo(0);

        // clear
        sll.clear();
        assertThat(sll.isEmpty()).isTrue();
    }

    @Test
    void reverseDemo() {
        Stack<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> sllOriginal = sll.copy();

        // reverse in place
        sll.reverse();

        // reverse copy
        Stack<Integer> sllCopy = sllOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(sll).isEqualTo(sllCopy);
    }
}
