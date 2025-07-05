package com.algolib.core.datastructures.lists;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class SinglyLinkedList<T extends Comparable<T>> extends LinkedList<T, SinglyListNode<T>> {
    public SinglyLinkedList() {
        super();
    }

    public SinglyLinkedList(T data) {
        super(data);
    }

    public SinglyLinkedList(T[] dataArray) {
        super(dataArray);
    }

    @Override
    protected SinglyListNode<T> createNode(T data) {
        return new SinglyListNode<>(data);
    }

    protected SinglyListNode<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        SinglyListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    private SinglyListNode<T> getPrevNodeAtIndex(int index) {
        if (index <= 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for previous node: " + index);
        }

        return getNodeAtIndex(index - 1);
    }

    private NodePair<T> getCurrentAndPrevNodeAtIndex(int index) {
        SinglyListNode<T> prev = getPrevNodeAtIndex(index-1);
        SinglyListNode<T> current = prev.getNext();

        return new NodePair<T>(prev, current);
    }

    public void add(T data) {
        SinglyListNode<T> newNode = new SinglyListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void addToHead(T data) {
        SinglyListNode<T> newNode = new SinglyListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Trying to add at an invalid index: " + index);
        }

        SinglyListNode<T> newNode = new SinglyListNode<>(data);

        // Insert at head
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode; // If list was empty
            }
        }
        // Insert at tail
        else if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
        }
        // Insert in the middle
        else {
            SinglyListNode<T> prev = getPrevNodeAtIndex(index);
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
        }

        size++;
    }

    public T deleteHead() {
        if (isEmpty()) {
            System.out.println("List is already empty.");
            return null;
        }

        SinglyListNode<T> oldHead = head;
        head = head.getNext();
        oldHead.setNext(null);
        size--;

        if (isEmpty()) {
            // List became empty after deletion
            tail = null;
        }

        return oldHead.getData();
    }

    public T deleteTail() {
        if (isEmpty()) {
            System.out.println("List is already empty.");
            return null;
        }

        SinglyListNode<T> oldTail = tail;

        if (head == tail) {
            // Only one element in the list
            head = tail = null;
        } else {
            SinglyListNode<T> prev = head;
            while (prev.getNext() != tail) {
                prev = prev.getNext();
            }
            prev.setNext(null);
            tail = prev;
        }

        size--;
        return oldTail.getData();
    }

    public T deleteAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Trying to delete an invalid index: " + index);
        }

        if (index == 0) {
            return deleteHead();
        } else if (index == size - 1) {
            return deleteTail();
        } else {
            NodePair<T> nodePair = getCurrentAndPrevNodeAtIndex(index);

            SinglyListNode<T> prev = nodePair.getPrev();
            SinglyListNode<T> nodeToDelete = nodePair.getCurrent();

            prev.setNext(nodeToDelete.getNext());
            nodeToDelete.setNext(null);

            size--;

            return nodeToDelete.getData();
        }
    }

    public void reverseLinkedList() {
        SinglyListNode<T> prev = null;
        SinglyListNode<T> curr = head;

        while (curr != null) {
            SinglyListNode<T> next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    public void reverseInGroups(int k) {
        if (head == null || k <= 1) return;

        head = reverseGroupHelper(head, k);
    }

    // Helper returns new head after reversing a group
    private SinglyListNode<T> reverseGroupHelper(SinglyListNode<T> node, int k) {
        SinglyListNode<T> curr = node;
        int count = 0;

        // Step 1: Check if there are at least k nodes to reverse
        while (curr != null && count < k) {
            curr = curr.getNext();
            count++;
        }

        if (count < k) return node; // Fewer than k nodes, no reversal

        // Step 2: Reverse k nodes
        SinglyListNode<T> prev = null;
        curr = node;
        for (int i = 0; i < k; i++) {
            SinglyListNode<T> next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        // Step 3: Recurse for next group and connect
        node.setNext(reverseGroupHelper(curr, k));

        // prev is the new head of this group
        return prev;
    }

    protected SinglyListNode<T> mergeTwoSortedLists(SinglyListNode<T> left, SinglyListNode<T> right) {
        // Dummy node to simplify appending and edge cases
        SinglyListNode<T> dummy = new SinglyListNode<>(null);
        SinglyListNode<T> tail = dummy;

        // Merging logic
        while (left != null && right != null) {
            if (left.getData().compareTo(right.getData()) <= 0) {
                tail.setNext(left);
                left = left.getNext();
            } else {
                tail.setNext(right);
                right = right.getNext();
            }
            tail = tail.getNext();
        }

        // Attach remaining nodes
        tail.setNext((left != null) ? left : right);

        return dummy.getNext();
    }

    public void removeDuplicates() {
        if (head == null) return;

        Set<T> seen = new HashSet<>();
        SinglyListNode<T> current = head;
        seen.add(current.getData());

        while (current.getNext() != null) {
            if (seen.contains(current.getNext().getData())) {
                // Duplicate found → remove it
                current.setNext(current.getNext().getNext());
                size--;
            } else {
                seen.add(current.getNext().getData());
                current = current.getNext();
            }
        }

        // Update tail
        tail = current;
    }

    public void generateCyclicList(int n, int cycleIndex, Function<Integer, T> generator) {
        if (n <= 0 || cycleIndex < 0 || cycleIndex >= n) {
            throw new IllegalArgumentException("Invalid size or cycle index");
        }

        head = null;
        tail = null;
        size = 0;

        SinglyListNode<T> cycleNode = null;
        SinglyListNode<T> prev = null;

        for (int i = 0; i < n; i++) {
            T value = generator.apply(i);
            SinglyListNode<T> newNode = new SinglyListNode<>(value);

            if (i == 0) {
                head = newNode;
            } else {
                prev.setNext(newNode);
            }

            if (i == cycleIndex) {
                cycleNode = newNode;
            }

            prev = newNode;
            size++;
        }

        tail = prev;
        tail.setNext(cycleNode);
    }

    private static class NodePair <T> {
        SinglyListNode<T> prev;
        SinglyListNode<T> current;

        NodePair(SinglyListNode<T> prev, SinglyListNode<T> current) {
            this.prev = prev;
            this.current = current;
        }

        public SinglyListNode<T> getPrev() {
            return prev;
        }

        public SinglyListNode<T> getCurrent() {
            return current;
        }
    }
}
