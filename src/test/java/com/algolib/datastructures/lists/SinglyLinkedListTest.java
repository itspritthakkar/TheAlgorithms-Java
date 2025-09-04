package com.algolib.datastructures.lists;

import com.algolib.core.datastructures.lists.SinglyLinkedList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SinglyLinkedListTest {

    @Test
    void equalityCheckDemo() {
        SinglyLinkedList<Integer> sl1 = new SinglyLinkedList<>(1);
        sl1.add(2);
        sl1.add(3);

        SinglyLinkedList<Integer> sl2 = new SinglyLinkedList<>(1);
        sl2.add(2);
        sl2.add(3);

        assertThat(sl2.isEqual(sl1)).isTrue();
    }

    @Test
    void crudDemo() {
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
    void reverseDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{4, 6, 7});
        sl.reverse();

        // original: 4 -> 6 -> 7
        // reversed: 7 -> 6 -> 4
        assertThat(sl.toList()).containsExactly(7, 6, 4);
    }

    @Test
    void reverseInGroupsDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        sl.reverseInGroups(3);

        // group of 3: (1,2,3) -> (4,5,6)
        // after reverse: 3,2,1, 6,5,4
        assertThat(sl.toList()).containsExactly(3, 2, 1, 6, 5, 4);
    }

    @Test
    void sortDemo() {
        Integer[] arr = {5, 3, 8, 1};
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(arr);

        sl.sort();

        assertThat(sl.toList()).containsExactly(1, 3, 5, 8);
    }

    @Test
    void removeDuplicatesDemo() {
        Integer[] arr = {1, 2, 2, 3, 3, 3, 4};
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(arr);

        sl.removeDuplicates();

        assertThat(sl.toList()).containsExactly(1, 2, 3, 4);
    }

    @Test
    void hasCycleDemo() {
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
}
