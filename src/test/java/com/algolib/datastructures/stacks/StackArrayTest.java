package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackArray;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackArrayTest {

    @Test
    void crud() {
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
    void reverseAndCopy() {
        Stack<Integer> sa = new StackArray<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> saOriginal = sa.copy();

        // reverse in place
        sa.reverse();

        // reverse copy
        Stack<Integer> saCopy = saOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(sa).isEqualTo(saCopy);
    }

    @Test
    void popOnEmptyThrows() {
        Stack<Integer> sa = new StackArray<>();
        assertThatThrownBy(sa::pop)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void peekOnEmptyThrows() {
        Stack<Integer> sa = new StackArray<>();
        assertThatThrownBy(sa::peek)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void constructorWithInvalidSizeThrows() {
        assertThatThrownBy(() -> new StackArray<>(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new StackArray<>(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void sizeAndIsEmptyAndIsFull() {
        StackArray<Integer> sa = new StackArray<>(2);
        assertThat(sa.size()).isZero();
        assertThat(sa.isEmpty()).isTrue();
        assertThat(sa.isFull()).isFalse();

        sa.push(1);
        sa.push(2);

        assertThat(sa.size()).isEqualTo(2);
        assertThat(sa.isEmpty()).isFalse();
        assertThat(sa.isFull()).isTrue();
    }

    @Test
    void triggersResizeWhenFull() {
        StackArray<Integer> sa = new StackArray<>(2);
        sa.push(1);
        sa.push(2);
        assertThat(sa.isFull()).isTrue();

        // trigger resize
        sa.push(3);

        assertThat(sa.size()).isEqualTo(3);
        assertThat(sa.peek()).isEqualTo(3);
    }

    @Test
    void toStringWorks() {
        StackArray<Integer> sa = new StackArray<>(new Integer[]{1, 2, 3});
        assertThat(sa.toString()).isEqualTo("[1, 2, 3]");

        StackArray<Integer> empty = new StackArray<>();
        assertThat(empty.toString()).isEqualTo("[]");
    }

    @Test
    void equalsAndHashCode() {
        StackArray<Integer> s1 = new StackArray<>(new Integer[]{1, 2, 3});
        StackArray<Integer> s2 = new StackArray<>(new Integer[]{1, 2, 3});
        StackArray<Integer> s3 = new StackArray<>(new Integer[]{1, 2, 4});
        StackArray<Integer> s4 = new StackArray<>(new Integer[]{1, 2});

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

    @Test
    void defaultConstructorCreatesEmptyStack() {
        StackArray<Integer> sa = new StackArray<>();
        assertThat(sa.size()).isZero();
        assertThat(sa.isEmpty()).isTrue();
        assertThat(sa.toString()).isEqualTo("[]");
    }

    @Test
    void constructorWithCapacityInitializesCorrectly() {
        StackArray<Integer> sa = new StackArray<>(5);
        assertThat(sa.size()).isZero();
        assertThat(sa.isEmpty()).isTrue();
        assertThat(sa.isFull()).isFalse();

        sa.push(1);
        assertThat(sa.size()).isEqualTo(1);
    }

    @Test
    void constructorWithArrayInitializesCorrectly() {
        StackArray<Integer> sa = new StackArray<>(new Integer[]{10, 20, 30});
        assertThat(sa.size()).isEqualTo(3);
        assertThat(sa.peek()).isEqualTo(30);
        assertThat(sa.toString()).isEqualTo("[10, 20, 30]");
    }
}
