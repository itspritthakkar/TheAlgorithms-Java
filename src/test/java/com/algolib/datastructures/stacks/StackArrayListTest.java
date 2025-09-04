package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StackArrayListTest {

    @Test
    void crudDemo() {
        Stack<Integer> saList = new StackArrayList<>(new Integer[]{1, 2, 3});

        // push
        Integer value = 42;
        saList.push(value);

        // peek
        Integer peeked = saList.peek();
        assertThat(peeked).isEqualTo(value);

        // pop
        Integer popped = saList.pop();
        assertThat(popped).isEqualTo(value);
        assertThat(saList.contains(value)).isFalse();

        // push + search
        Integer value2 = 99;
        saList.push(value2);
        int foundIndex = saList.search(value2);
        assertThat(foundIndex).isGreaterThanOrEqualTo(0);

        // clear
        saList.clear();
        assertThat(saList.isEmpty()).isTrue();
    }

    @Test
    void reverseDemo() {
        Stack<Integer> saList = new StackArrayList<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> saListOriginal = saList.copy();

        // reverse in place
        saList.reverse();

        // reverse copy
        Stack<Integer> saListCopy = saListOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(saList).isEqualTo(saListCopy);
    }
}
