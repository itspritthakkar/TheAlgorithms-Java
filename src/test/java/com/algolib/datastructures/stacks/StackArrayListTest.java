package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackArrayListTest {

    @Test
    void crud() {
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
    void reverse() {
        Stack<Integer> saList = new StackArrayList<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> saListOriginal = saList.copy();

        // reverse in place
        saList.reverse();

        // reverse copy
        Stack<Integer> saListCopy = saListOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(saList).isEqualTo(saListCopy);
    }

    @Test
    void popOnEmptyThrows() {
        Stack<Integer> saList = new StackArrayList<>();
        assertThatThrownBy(saList::pop)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void peekOnEmptyThrows() {
        Stack<Integer> saList = new StackArrayList<>();
        assertThatThrownBy(saList::peek)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void sizeAndIsEmpty() {
        StackArrayList<Integer> saList = new StackArrayList<>();
        assertThat(saList.size()).isZero();
        assertThat(saList.isEmpty()).isTrue();

        saList.push(10);
        assertThat(saList.size()).isEqualTo(1);
        assertThat(saList.isEmpty()).isFalse();
    }

    @Test
    void toStringWorks() {
        StackArrayList<Integer> saList = new StackArrayList<>(new Integer[]{1, 2, 3});
        assertThat(saList.toString()).isEqualTo("[1, 2, 3]");
    }

    @Test
    void equalsAndHashCode() {
        StackArrayList<Integer> s1 = new StackArrayList<>(new Integer[]{1, 2, 3});
        StackArrayList<Integer> s2 = new StackArrayList<>(new Integer[]{1, 2, 3});
        StackArrayList<Integer> s3 = new StackArrayList<>(new Integer[]{1, 2, 4});
        StackArrayList<Integer> s4 = new StackArrayList<>(new Integer[]{1, 2});

        // reflexive
        assertThat(s1).isEqualTo(s1);

        // symmetric
        assertThat(s1).isEqualTo(s2);
        assertThat(s2).isEqualTo(s1);
        assertThat(s1.hashCode()).isEqualTo(s2.hashCode());

        // different content
        assertThat(s1).isNotEqualTo(s3);

        // different size
        assertThat(s1).isNotEqualTo(s4);

        // null and different type
        assertThat(s1.equals(null)).isFalse();
        assertThat(s1.equals("not a stack")).isFalse();
    }
}
