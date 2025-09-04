package com.algolib.datastructures.lists;

import com.algolib.core.datastructures.lists.SinglyLinkedList;
import com.algolib.core.datastructures.lists.SinglyListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SinglyLinkedListTest {

    @Test
    void equalityCheck() {
        SinglyLinkedList<Integer> sl1 = new SinglyLinkedList<>(1);
        sl1.add(2);
        sl1.add(3);

        SinglyLinkedList<Integer> sl2 = new SinglyLinkedList<>(1);
        sl2.add(2);
        sl2.add(3);

        assertThat(sl2.isEqual(sl1)).isTrue();
    }

    @Test
    void crud() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{4, 6, 7});

        sl.addToHead(8); // list = 8,4,6,7
        sl.addAtIndex(3, 12); // list = 8,4,6,12,7

        int oldHeadData = sl.deleteHead(); // deletes 8
        int oldTailData = sl.deleteTail(); // deletes 7
        int oldIndexData = sl.deleteAtIndex(2); // deletes 12

        int searchNodeIndex = sl.getNodeIndexByData(4);

        assertThat(oldHeadData).isEqualTo(8);
        assertThat(oldTailData).isEqualTo(7);
        assertThat(oldIndexData).isEqualTo(12);
        assertThat(searchNodeIndex).isEqualTo(0);
    }

    @Test
    void reverse() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{4, 6, 7});
        sl.reverse();

        // original: 4 -> 6 -> 7
        // reversed: 7 -> 6 -> 4
        assertThat(sl.toList()).containsExactly(7, 6, 4);
    }

    @Test
    void reverseInGroups() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        sl.reverseInGroups(3);

        // group of 3: (1,2,3) -> (4,5,6)
        // after reverse: 3,2,1, 6,5,4
        assertThat(sl.toList()).containsExactly(3, 2, 1, 6, 5, 4);
    }

    @Test
    void sort() {
        Integer[] arr = {5, 3, 8, 1};
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(arr);

        sl.sort();

        assertThat(sl.toList()).containsExactly(1, 3, 5, 8);
    }

    @Test
    void removeDuplicates() {
        Integer[] arr = {1, 2, 2, 3, 3, 3, 4};
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(arr);

        sl.removeDuplicates();

        assertThat(sl.toList()).containsExactly(1, 2, 3, 4);
    }

    @Test
    void hasCycle() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
        sl.generateCyclicList(5, 2, Integer::valueOf);

        assertThat(sl.hasCycle()).isTrue();
        assertThat(sl.detectStartOfCycle()).isNotNull();
    }

    @Test
    void swapDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        sl.swap(1, 3);

        // swapped indices 1 and 3 (0-based): 1,4,3,2,5
        assertThat(sl.toList()).containsExactly(1, 4, 3, 2, 5);
    }

    @Test
    void constructorEmptyAndSingleElement() {
        SinglyLinkedList<Integer> empty = new SinglyLinkedList<>();
        assertThat(empty.isEmpty()).isTrue();

        SinglyLinkedList<Integer> single = new SinglyLinkedList<>(42);
        assertThat(single.toList()).containsExactly(42);
    }

    @Test
    void deleteOnEmptyList() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
        assertThat(sl.deleteHead()).isNull();
        assertThat(sl.deleteTail()).isNull();
    }

    @Test
    void deleteAtIndexInvalid() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        assertThatThrownBy(() -> sl.deleteAtIndex(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sl.deleteAtIndex(3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void addAtIndexInvalid() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
        assertThatThrownBy(() -> sl.addAtIndex(5, 10))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void clearEmptiesList() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        sl.clear();
        assertThat(sl.isEmpty()).isTrue();
        assertThat(sl.toList()).isEmpty();
    }

    @Test
    void copyProducesIndependentList() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        SinglyLinkedList<Integer> copy = sl.copy();

        assertThat(copy.toList()).containsExactly(1, 2, 3);
        sl.add(4);
        assertThat(copy.toList()).doesNotContain(4); // proves independence
    }

    @Test
    void toStringWorks() {
        SinglyLinkedList<Integer> empty = new SinglyLinkedList<>();
        assertThat(empty.toString()).isEmpty();

        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        assertThat(sl.toString()).isEqualTo("1 -> 2 -> 3");
    }

    @Test
    void reverseInGroupsWithInvalidKDoesNothing() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        sl.reverseInGroups(1); // should no-op
        assertThat(sl.toList()).containsExactly(1, 2, 3);
    }

    @Test
    void generateCyclicListInvalidArgs() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();
        assertThatThrownBy(() -> sl.generateCyclicList(0, 0, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sl.generateCyclicList(5, -1, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sl.generateCyclicList(5, 5, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deleteAtIndexFromMiddle() {
        // list = 1,2,3,4,5
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});

        int deleted = sl.deleteAtIndex(2); // should delete element '3'

        assertThat(deleted).isEqualTo(3);
        assertThat(sl.toList()).containsExactly(1, 2, 4, 5);
        assertThat(sl.getNodeIndexByData(4)).isEqualTo(2); // index of 4 shifted left
    }

    @Test
    void singlyListNodeEqualityAndHashCode() {
        SinglyListNode<Integer> n1 = new SinglyListNode<>(5);
        SinglyListNode<Integer> n2 = new SinglyListNode<>(5);
        SinglyListNode<Integer> n3 = new SinglyListNode<>(6);

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

        // same data, same next
        SinglyListNode<Integer> next1 = new SinglyListNode<>(10);
        SinglyListNode<Integer> next2 = new SinglyListNode<>(10);
        n1.setNext(next1);
        n2.setNext(next2);
        assertThat(n1).isEqualTo(n2);

        // same data, different next
        SinglyListNode<Integer> x = new SinglyListNode<>(20);
        n2.setNext(x);
        assertThat(n1).isNotEqualTo(n2);
    }
}
