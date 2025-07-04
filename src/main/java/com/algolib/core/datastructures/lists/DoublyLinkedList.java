package com.algolib.core.datastructures.lists;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class DoublyLinkedList<T extends Comparable<T>> extends LinkedList<T, DoublyListNode<T>> {
    public DoublyLinkedList() {
        super();
    }

    public DoublyLinkedList(T data) {
        super(data);
    }

    public DoublyLinkedList(T[] dataArray) {
        super(dataArray);
    }

    @Override
    protected DoublyListNode<T> createNode(T data) {
        return new DoublyListNode<>(data);
    }

    protected DoublyListNode<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        DoublyListNode<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.getPrev();
            }
        }
        return node;
    }

    public void add(T data) {
        DoublyListNode<T> newNode = new DoublyListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    public void addToHead(T data) {
        DoublyListNode<T> newNode = new DoublyListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Trying to add at an invalid index: " + index);
        }

        DoublyListNode<T> newNode = new DoublyListNode<>(data);

        // Insert at head
        if (index == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrev(newNode);
            }
            head = newNode;
            if (size == 0) {
                tail = newNode; // If list was empty
            }
        }
        // Insert at tail
        else if (index == size) {
            if (tail != null) {
                tail.setNext(newNode);
            }
            newNode.setPrev(tail);
            tail = newNode;
        }
        // Insert in the middle
        else {
            DoublyListNode<T> oldNode = getNodeAtIndex(index);
            DoublyListNode<T> prev = oldNode.getPrev();

            prev.setNext(newNode);
            newNode.setPrev(prev);
            newNode.setNext(oldNode);
            oldNode.setPrev(newNode);
        }

        size++;
    }

    public T deleteHead() {
        if (isEmpty()) {
            System.out.println("List is already empty.");
            return null;
        }

        DoublyListNode<T> oldHead = head;
        head = head.getNext();
        head.setPrev(null);
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

        DoublyListNode<T> oldTail = tail;

        if (head == tail) {
            // Only one element in the list
            head = tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }

        oldTail.setPrev(null);

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
            DoublyListNode<T> nodeToDelete = getNodeAtIndex(index);
            DoublyListNode<T> prev = nodeToDelete.getPrev();
            DoublyListNode<T> next = nodeToDelete.getNext();

            prev.setNext(next);
            next.setPrev(prev);

            // Clear references for GC
            nodeToDelete.setNext(null);
            nodeToDelete.setPrev(null);

            size--;
            return nodeToDelete.getData();
        }
    }

    public void reverseLinkedList() {
        DoublyListNode<T> current = head;
        DoublyListNode<T> oldPrev = null;

        // Swap next and prev for all nodes
        while (current != null) {
            oldPrev = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(oldPrev);
            current = current.getPrev(); // Move to "next" node, which is original prev
        }

        // Swap head and tail
        if (oldPrev != null) {
            // oldPrev.prev is the new head after reversal
            DoublyListNode<T> oldHead = head;
            head = oldPrev.getPrev();
            tail = oldHead;
        }
    }

    public void reverseInGroups(int k) {
        if (head == null || k <= 1) return;
        head = reverseGroupHelper(head, k);

        // Recalculate tail
        DoublyListNode<T> oldHead = head;
        while (oldHead.getNext() != null) oldHead = oldHead.getNext();
        tail = oldHead;
    }

    // Returns new head of the group
    private DoublyListNode<T> reverseGroupHelper(DoublyListNode<T> node, int k) {
        DoublyListNode<T> current = node;
        int count = 0;

        // Check if there are at least k nodes
        while (current != null && count < k) {
            current = current.getNext();
            count++;
        }

        if (count < k) return node;

        // Reverse k nodes
        DoublyListNode<T> prev = null;
        current = node;

        for (int i = 0; i < k; i++) {
            DoublyListNode<T> oldNext = current.getNext();

            current.setNext(prev);
            current.setPrev(oldNext);

            prev = current;
            current = oldNext;
        }

        // Recurse on remaining list
        DoublyListNode<T> newNextHead = reverseGroupHelper(current, k);

        // Connect reversed group to the next group
        node.setNext(newNextHead);
        if (newNextHead != null) newNextHead.setPrev(node);

        // Set prev of new head to null
        if (prev != null) {
            prev.setPrev(null);
        }

        return prev;
    }

    protected DoublyListNode<T> mergeTwoSortedLists(DoublyListNode<T> left, DoublyListNode<T> right) {
        // Dummy node to simplify appending and edge cases
        DoublyListNode<T> dummy = new DoublyListNode<>(null);
        DoublyListNode<T> tail = dummy;

        // Merging logic
        while (left != null && right != null) {
            if (left.getData().compareTo(right.getData()) <= 0) {
                tail.setNext(left);
                left.setPrev(tail);
                left = left.getNext();
            } else {
                tail.setNext(right);
                right.setPrev(tail);
                right = right.getNext();
            }
            tail = tail.getNext();
        }

        // Attach remaining nodes
        if (left != null) {
            tail.setNext(left);
            left.setPrev(tail);
        } else {
            tail.setNext(right);
            right.setPrev(tail);
        }

        dummy.getNext().setPrev(null);
        return dummy.getNext();
    }

    public void removeDuplicates() {
        if (head == null) return;

        Set<T> seen = new HashSet<>();
        DoublyListNode<T> current = head;
        seen.add(current.getData());

        while (current.getNext() != null) {
            if (seen.contains(current.getNext().getData())) {
                // Duplicate found → remove it
                DoublyListNode<T> duplicate = current.getNext();
                current.setNext(duplicate.getNext());

                if (duplicate.getNext() != null) {
                    duplicate.getNext().setPrev(current);
                }

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

        DoublyListNode<T> cycleNode = null;
        DoublyListNode<T> prev = null;

        for (int i = 0; i < n; i++) {
            T value = generator.apply(i);
            DoublyListNode<T> newNode = new DoublyListNode<>(value);

            if (i == 0) {
                head = newNode;
            } else {
                prev.setNext(newNode);
                newNode.setPrev(prev);
            }

            if (i == cycleIndex) {
                cycleNode = newNode;
            }

            prev = newNode;
            size++;
        }

        tail = prev;
        tail.setNext(cycleNode);
        if (cycleNode != null) {
            cycleNode.setPrev(tail);
        }
    }

    public void printListBackward() {
        DoublyListNode<T> current = tail;
        Set<DoublyListNode<T>> visited = new HashSet<>();

        while (current != null) {
            // Detect cycle
            if (visited.contains(current)) {
                System.out.print(current.getData() + " <- (cycle detected here)");
                break;
            }

            System.out.print(current.getData() + " <- ");
            visited.add(current);
            current = current.getPrev();
        }

        if (current == null) {
            System.out.println("null");
        } else {
            System.out.println(); // in case of cycle
        }

        System.out.println("List size (logical count): " + getSize());
    }
}
