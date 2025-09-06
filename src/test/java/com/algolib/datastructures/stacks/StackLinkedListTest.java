package com.algolib.datastructures.stacks;

import com.algolib.core.datastructures.stacks.Stack;
import com.algolib.core.datastructures.stacks.StackLinkedList;
import com.algolib.core.datastructures.stacks.StackNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackLinkedListTest {

    @Test
    void crud() {
        StackLinkedList<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3});

        // push
        sll.push(42);
        assertThat(sll.peek()).isEqualTo(42);
        assertThat(sll.size()).isEqualTo(4);

        // pop
        Integer popped = sll.pop();
        assertThat(popped).isEqualTo(42);
        assertThat(sll.contains(42)).isFalse();

        // search
        sll.push(99);
        int foundIndex = sll.search(99);
        assertThat(foundIndex).isGreaterThanOrEqualTo(0);

        // clear
        sll.clear();
        assertThat(sll.isEmpty()).isTrue();
        assertThat(sll.size()).isZero();
    }

    @Test
    void reverse() {
        Stack<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3, 4});
        Stack<Integer> sllOriginal = sll.copy();

        // reverse in place
        sll.reverse();

        // reverse copy
        Stack<Integer> sllCopy = sllOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(sll).isEqualTo(sllCopy);
    }

    @Test
    void constructors() {
        StackLinkedList<Integer> empty = new StackLinkedList<>();
        assertThat(empty.isEmpty()).isTrue();
        assertThat(empty.size()).isZero();

        StackLinkedList<Integer> single = new StackLinkedList<>(42);
        assertThat(single.isEmpty()).isFalse();
        assertThat(single.size()).isEqualTo(1);
        assertThat(single.peek()).isEqualTo(42);

        StackLinkedList<Integer> fromArray = new StackLinkedList<>(new Integer[]{1, 2, 3});
        assertThat(fromArray.size()).isEqualTo(3);
        assertThat(fromArray.contains(2)).isTrue();
    }

    @Test
    void popOnEmptyThrows() {
        StackLinkedList<Integer> sll = new StackLinkedList<>();
        assertThatThrownBy(sll::pop)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stack is empty");
    }

    @Test
    void peekOnEmptyThrows() {
        StackLinkedList<Integer> sll = new StackLinkedList<>();
        assertThatThrownBy(sll::peek)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stack is empty");
    }

    @Test
    void toArrayAndToString() {
        StackLinkedList<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3});

        Integer[] arr = sll.toArray(Integer[]::new);
        assertThat(arr).containsExactly(1, 2, 3);

        assertThat(sll.toString()).isEqualTo("1 -> 2 -> 3");

        sll.clear();
        assertThat(sll.toString()).isNull();
    }

    @Test
    void equalsAndHashCode() {
        StackLinkedList<Integer> s1 = new StackLinkedList<>(new Integer[]{1, 2, 3});
        StackLinkedList<Integer> s2 = new StackLinkedList<>(new Integer[]{1, 2, 3});
        StackLinkedList<Integer> s3 = new StackLinkedList<>(new Integer[]{1, 2, 4});
        StackLinkedList<Integer> s4 = new StackLinkedList<>(new Integer[]{1, 2});

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
    void reverseAndCopy() {
        StackLinkedList<Integer> sll = new StackLinkedList<>(new Integer[]{1, 2, 3, 4});
        StackLinkedList<Integer> sllOriginal = sll.copy();

        // reverse in place
        sll.reverse();

        // reverse copy
        StackLinkedList<Integer> sllCopy = sllOriginal.reverseCopy();

        // after reverse, stacks should be equal
        assertThat(sll).isEqualTo(sllCopy);
    }

    @Test
    void stackNodeEqualityAndHashCode() {
        StackNode<Integer> n1 = new StackNode<>(5);
        StackNode<Integer> n2 = new StackNode<>(5);
        StackNode<Integer> n3 = new StackNode<>(6);

        // reflexive
        assertThat(n1.equals(n1)).isTrue();

        // null & different class
        assertThat(n1.equals(null)).isFalse();
        assertThat(n1.equals("not a node")).isFalse();

        // same data, no next
        assertThat(n1).isEqualTo(n2);
        assertThat(n1.hashCode()).isEqualTo(n2.hashCode());

        // different data
        assertThat(n1).isNotEqualTo(n3);

        // same data + same next
        StackNode<Integer> next1 = new StackNode<>(10);
        StackNode<Integer> next2 = new StackNode<>(10);
        n1.setNext(next1);
        n2.setNext(next2);
        assertThat(n1).isEqualTo(n2);

        // same data but different next
        n2.setNext(new StackNode<>(20));
        assertThat(n1).isNotEqualTo(n2);
    }

    @Test
    void stackNodeNextSetterAndGetter() {
        StackNode<Integer> node = new StackNode<>(100);
        StackNode<Integer> next = new StackNode<>(200);

        node.setNext(next);

        assertThat(node.getNext()).isEqualTo(next);
        assertThat(node.getNext().getData()).isEqualTo(200);
    }
}
