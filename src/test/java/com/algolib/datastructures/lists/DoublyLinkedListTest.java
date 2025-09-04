package com.algolib.datastructures.lists;

import com.algolib.core.datastructures.lists.DoublyLinkedList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoublyLinkedListTest {

    @Test
    void equalityCheckDemo() {
        DoublyLinkedList<Integer> dl1 = new DoublyLinkedList<>(1);
        dl1.add(2);
        dl1.add(3);

        DoublyLinkedList<Integer> dl2 = new DoublyLinkedList<>(1);
        dl2.add(2);
        dl2.add(3);

        assertThat(dl2.isEqual(dl1)).isTrue();
    }

    @Test
    void crudDemo() {
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
    void reverseDemo() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{4, 6, 7});
        dl.reverse();

        // original: 4 <-> 6 <-> 7
        // reversed: 7 <-> 6 <-> 4
        assertThat(dl.toList()).containsExactly(7, 6, 4);
    }

    @Test
    void reverseInGroupsDemo() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        dl.reverseInGroups(3);

        // group of 3: (1,2,3) <-> (4,5,6)
        // after reverse: 3,2,1, 6,5,4
        assertThat(dl.toList()).containsExactly(3, 2, 1, 6, 5, 4);
    }

    @Test
    void sortDemo() {
        Integer[] arr = {5, 3, 8, 1};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(arr);

        dl.sort();

        // sorted: 1,3,5,8
        assertThat(dl.toList()).containsExactly(1, 3, 5, 8);
    }

    @Test
    void removeDuplicatesDemo() {
        Integer[] arr = {1, 2, 2, 3, 3, 4};
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(arr);

        dl.removeDuplicates();

        // duplicates removed: 1,2,3,4
        assertThat(dl.toList()).containsExactly(1, 2, 3, 4);
    }

    @Test
    void hasCycleDemo() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>();
        dl.generateCyclicList(5, 2, Integer::valueOf);

        assertThat(dl.hasCycle()).isTrue();
        assertThat(dl.detectStartOfCycle()).isNotNull();
    }

    @Test
    void swapDemo() {
        DoublyLinkedList<Integer> dl = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        dl.swap(1, 3);

        // swapped indices 1 and 3 (0-based): 1,4,3,2,5
        assertThat(dl.toList()).containsExactly(1, 4, 3, 2, 5);
    }
}
