package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackArray;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackArrayTest {

    @Test
    void crudDemo() {
        Stack<Integer> sa = new StackArray<>(new Integer[]{1, 2, 3});

        // push
        Integer value = 42;
        sa.push(value);

        // peek
        Integer peeked = sa.peek();
        assertThat(peeked).isEqualTo(value);

        // pop
        Integer popped = sa.pop();
        assertThat(popped).isEqualTo(value);
        assertThat(sa.contains(value)).isFalse();

        // push + search
        Integer value2 = 99;
        sa.push(value2);
        int foundIndex = sa.search(value2);
        assertThat(foundIndex).isGreaterThanOrEqualTo(0);

        // clear
        sa.clear();
        assertThat(sa.isEmpty()).isTrue();
    }

    @Test
    void reverseDemo() {
        Stack<Integer> sa = new StackArray<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> saOriginal = sa.copy();

        // reverse in place
        sa.reverse();

        // reverse copy
        Stack<Integer> saCopy = saOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(sa).isEqualTo(saCopy);
    }
}
