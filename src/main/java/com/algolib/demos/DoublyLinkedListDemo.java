package com.practice.demos;

import com.practice.core.DoublyLinkedList;
import com.practice.core.SinglyLinkedList;

public class DoublyLinkedListDemo implements Demoable{
    @Override
    public void start() {
        System.out.println("================= Doubly linked list demo =================");

//        equalityCheckDemo();
//        crudDemo();
        reverseDemo();
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
        DoublyLinkedList<Integer> sl = new DoublyLinkedList<>(new Integer[]{4, 6, 7, 5, 22});

        sl.reverseLinkedList();

        sl.printList();
    }
}
