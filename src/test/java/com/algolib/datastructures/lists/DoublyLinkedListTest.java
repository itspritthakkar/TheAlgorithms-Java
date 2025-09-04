package com.algolib.datastructures.lists;

import com.algolib.core.datastructures.lists.DoublyLinkedList;
import com.algolib.core.datastructures.lists.DoublyListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DoublyLinkedListTest {

    @Test
    void equalityCheck() {
        DoublyLinkedList<Integer> dl1 = new DoublyLinkedList<>(1);
        dl1.add(2);
        dl1.add(3);

        DoublyLinkedList<Integer> dl2 = new DoublyLinkedList<>(1);
        dl2.add(2);
        dl2.add(3);

        assertThat(dl2.isEqual(dl1)).isTrue();
    }

    @Test
    void crud() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{4, 6, 7});

        dl.addToHead(8); // list = 8,4,6,7
        dl.addAtIndex(3, 12); // list = 8,4,6,12,7

        int oldHeadData = dl.deleteHead(); // deletes 8
        int oldTailData = dl.deleteTail(); // deletes 7
        int oldIndexData = dl.deleteAtIndex(2); // deletes 12

        int searchNodeIndex = dl.getNodeIndexByData(4);

        assertThat(oldHeadData).isEqualTo(8);
        assertThat(oldTailData).isEqualTo(7);
        assertThat(oldIndexData).isEqualTo(12);
        assertThat(searchNodeIndex).isEqualTo(0);
    }

    @Test
    void reverse() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{4, 6, 7});
        dl.reverse();

        // original: 4 <-> 6 <-> 7
        // reversed: 7 <-> 6 <-> 4
        assertThat(dl.toList()).containsExactly(7, 6, 4);
    }

    @Test
    void reverseInGroups() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        dl.reverseInGroups(3);

        // group of 3: (1,2,3) <-> (4,5,6)
        // after reverse: 3,2,1, 6,5,4
        assertThat(dl.toList()).containsExactly(3, 2, 1, 6, 5, 4);
    }

    @Test
    void sort() {
        Integer[] arr = {5, 3, 8, 1};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(arr);

        dl.sort();

        // sorted: 1,3,5,8
        assertThat(dl.toList()).containsExactly(1, 3, 5, 8);
    }

    @Test
    void removeDuplicates() {
        Integer[] arr = {1, 2, 2, 3, 3, 4};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(arr);

        dl.removeDuplicates();

        // duplicates removed: 1,2,3,4
        assertThat(dl.toList()).containsExactly(1, 2, 3, 4);
    }

    @Test
    void hasCycle() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        dl.generateCyclicList(5, 2, Integer::valueOf);

        assertThat(dl.hasCycle()).isTrue();
        assertThat(dl.detectStartOfCycle()).isNotNull();
    }

    @Test
    void swap() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        dl.swap(1, 3);

        // swapped indices 1 and 3 (0-based): 1,4,3,2,5
        assertThat(dl.toList()).containsExactly(1, 4, 3, 2, 5);
    }

    @Test
    void constructorEmptyAndSingleElement() {
        DoublyLinkedList<Integer> empty = new DoublyLinkedList<>();
        assertThat(empty.isEmpty()).isTrue();

        DoublyLinkedList<Integer> single = new DoublyLinkedList<>(42);
        assertThat(single.toList()).containsExactly(42);
    }

    @Test
    void deleteOnEmptyListReturnsNull() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        assertThat(dl.deleteHead()).isNull();
        assertThat(dl.deleteTail()).isNull();
    }

    @Test
    void deleteAtIndexInvalidThrows() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        assertThatThrownBy(() -> dl.deleteAtIndex(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> dl.deleteAtIndex(3))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void addAtIndexInvalidThrows() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        assertThatThrownBy(() -> dl.addAtIndex(5, 10))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void deleteAtIndexFromMiddle() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        int deleted = dl.deleteAtIndex(2); // deletes 3
        assertThat(deleted).isEqualTo(3);
        assertThat(dl.toList()).containsExactly(1, 2, 4, 5);
    }

    @Test
    void clearEmptiesList() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        dl.clear();
        assertThat(dl.isEmpty()).isTrue();
    }

    @Test
    void copyProducesIndependentList() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        DoublyLinkedList<Integer> copy = dl.copy();

        assertThat(copy.toList()).containsExactly(1, 2, 3);
        dl.add(4);
        assertThat(copy.toList()).doesNotContain(4);
    }

    @Test
    void toStringVariants() {
        DoublyLinkedList<Integer> empty = new DoublyLinkedList<>();
        assertThat(empty.toString()).isEqualTo("empty");

        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        assertThat(dl.toString()).isEqualTo("1 <-> 2 <-> 3");

        // cyclic
        dl.generateCyclicList(3, 0, Integer::valueOf);
        assertThat(dl.toString()).contains("cycle detected");
    }

    @Test
    void reverseInGroupsNoEffectWhenKTooSmallOrLarge() {
        DoublyLinkedList<Integer> dl1 = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        dl1.reverseInGroups(1);
        assertThat(dl1.toList()).containsExactly(1, 2, 3);

        DoublyLinkedList<Integer> dl2 = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        dl2.reverseInGroups(5);
        assertThat(dl2.toList()).containsExactly(1, 2, 3);
    }

    @Test
    void generateCyclicListInvalidArgsThrows() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        assertThatThrownBy(() -> dl.generateCyclicList(0, 0, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> dl.generateCyclicList(5, -1, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> dl.generateCyclicList(5, 5, Integer::valueOf))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void printListBackwardRuns() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        dl.printListBackward(); // just ensure no exceptions
    }

    @Test
    void doublyListNodeEqualityAndHashCode() {
        DoublyListNode<Integer> n1 = new DoublyListNode<>(5);
        DoublyListNode<Integer> n2 = new DoublyListNode<>(5);
        DoublyListNode<Integer> n3 = new DoublyListNode<>(6);

        // reflexive
        assertThat(n1.equals(n1)).isTrue();

        // null & different class
        assertThat(n1.equals(null)).isFalse();
        assertThat(n1.equals("not a node")).isFalse();

        // same data, no next/prev
        assertThat(n1).isEqualTo(n2);
        assertThat(n1.hashCode()).isEqualTo(n2.hashCode());

        // different data
        assertThat(n1).isNotEqualTo(n3);

        // same data + same next + same prev
        DoublyListNode<Integer> next1 = new DoublyListNode<>(10);
        DoublyListNode<Integer> next2 = new DoublyListNode<>(10);
        DoublyListNode<Integer> prev1 = new DoublyListNode<>(1);
        DoublyListNode<Integer> prev2 = new DoublyListNode<>(1);

        n1.setNext(next1);
        n2.setNext(next2);
        n1.setPrev(prev1);
        n2.setPrev(prev2);

        assertThat(n1).isEqualTo(n2);

        // same data but different next
        n2.setNext(new DoublyListNode<>(20));
        assertThat(n1).isNotEqualTo(n2);

        // same data but different prev
        n2.setNext(next2); // restore same next
        n2.setPrev(new DoublyListNode<>(99));
        assertThat(n1).isNotEqualTo(n2);
    }

    @Test
    void doublyListNodePrevSetterAndGetter() {
        DoublyListNode<Integer> node = new DoublyListNode<>(100);
        DoublyListNode<Integer> prev = new DoublyListNode<>(50);

        node.setPrev(prev);

        assertThat(node.getPrev()).isEqualTo(prev);
    }
}
