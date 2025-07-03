package com.practice.demos;

import com.practice.core.SinglyLinkedList;
import com.practice.utils.helpers.ArrayHelper;

public class SinglyLinkedListDemo implements Demoable {

    @Override
    public void start() {
        try {
            System.out.println("================= Singly linked list demo =================");

            equalityCheckDemo();
            crudDemo();
            reverseDemo();
            reverseInGroupsDemo();
            sortDemo();
            removeDuplicatesDemo();
            hasCycleDemo();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void equalityCheckDemo() {
        SinglyLinkedList<Integer> sl1 = new SinglyLinkedList<>(1);
        sl1.add(2);
        sl1.add(3);

        SinglyLinkedList<Integer> sl2 = new SinglyLinkedList<>(1);
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
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{4, 6, 7});

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
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{4, 6, 7, 5, 22, 45, 84, 43, 12});

        sl.reverseLinkedList();

        sl.printList();
    }

    private void reverseInGroupsDemo() {
        SinglyLinkedList<Integer> sl5 = new SinglyLinkedList<>(new Integer[]{4, 6, 7, 5, 22, 45, 84, 43, 12});

        sl5.reverseInGroups(3);

        sl5.printList();
    }

    private void sortDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(ArrayHelper.generateRandomArray(10, 1, 50));
        System.out.println("Random generated linked list:");
        sl.printList();

        sl.sort();

        System.out.println("Sorted linked list:");
        sl.printList();
    }

    private void removeDuplicatesDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(ArrayHelper.generateRandomArray(10, 1, 10));
        System.out.println("Random generated linked list:");
        sl.printList();

        sl.removeDuplicates();

        System.out.println("Duplicate less list:");
        sl.printList();
    }

    private void hasCycleDemo() {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<>();

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
}
