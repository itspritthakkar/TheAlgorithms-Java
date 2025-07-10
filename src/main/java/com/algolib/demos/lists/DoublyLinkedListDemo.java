package com.algolib.demos.lists;

import com.algolib.core.datastructures.lists.DoublyLinkedList;
import com.algolib.demos.Demoable;
import com.algolib.utils.helpers.ArrayHelper;

public class DoublyLinkedListDemo implements Demoable {
    @Override
    public void start() {
        System.out.println("================= Doubly linked list demo =================");

        equalityCheckDemo();
        crudDemo();
        reverseDemo();
        reverseInGroupsDemo();
        sortDemo();
        removeDuplicatesDemo();
        hasCycleDemo();
        swapDemo();
    }

    private void equalityCheckDemo() {
        DoublyLinkedList<Integer> sl1 = new DoublyLinkedList<>(1);
        sl1.add(2);
        sl1.add(3);

        DoublyLinkedList<Integer> sl2 = new DoublyLinkedList<>(1);
        sl2.add(2);
        sl2.add(3);

        sl1.printList();
        sl2.printList();

        boolean isSl2EqualToSl1 = sl2.isEqual(sl1);

        if (isSl2EqualToSl1) {
            System.out.println("Sl2 is Equal to Sl1");
        } else {
            System.out.println("Sl2 is not Equal to Sl1");
        }
    }

    private void crudDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(new Integer[]{4, 6, 7});

        sl.addToHead(8);
        sl.addAtIndex(3, 12);

        int oldHeadData = sl.deleteHead();
        int oldTailData = sl.deleteTail();

        int indexToDelete = 2;
        int oldIndexData = sl.deleteAtIndex(indexToDelete);

        int dataToSearch = 6;
        int searchNodeIndex = sl.getNodeIndexByData(dataToSearch);

        sl.printList();

        System.out.println("Head data that was deleted: " + oldHeadData);
        System.out.println("Tail data that was deleted: " + oldTailData);
        System.out.printf("Deleted index %d data: %d%n", indexToDelete, oldIndexData);
        System.out.printf("Found node with data %d at index: %d%n", dataToSearch, searchNodeIndex);
    }

    private void reverseDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(new Integer[]{4, 6, 7, 5, 22, 45, 84, 43, 12});

        sl.reverseLinkedList();

        sl.printList();
    }

    private void reverseInGroupsDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(new Integer[]{4, 6, 7, 5, 22, 45, 84, 43, 12});

        sl.reverseInGroups(3);

        sl.printList();
        sl.printListBackward();
    }

    private void sortDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(ArrayHelper.generateRandomArray(10, 1, 50));
        System.out.println("Random generated linked list:");
        sl.printList();

        sl.sort();

        System.out.println("Sorted linked list:");
        sl.printList();
    }

    private void removeDuplicatesDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(ArrayHelper.generateRandomArray(10, 1, 10));
        System.out.println("Random generated linked list:");
        sl.printList();

        sl.removeDuplicates();

        System.out.println("Duplicate less list:");
        sl.printList();
    }

    private void hasCycleDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>();

        sl.generateCyclicList(20, 2, Integer::valueOf);

        sl.printList();

        boolean hasCycle = sl.hasCycle();
        Integer startPoint = sl.detectStartOfCycle();

        if (hasCycle) {
            System.out.println("The list is cyclic with starting point: " + startPoint);
        } else {
            System.out.println("The list is not cyclic");
        }
    }

    private void swapDemo() {
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(ArrayHelper.generateRandomArray(10, 1, 10));
        System.out.println("Random generated linked list:");
        sl.printList();

        sl.swap(2, 7);

        System.out.println("List after swap:");
        sl.printList();
    }
}
