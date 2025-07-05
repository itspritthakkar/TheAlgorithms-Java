package com.algolib.core.datastructures.lists;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public abstract class LinkedList<T extends Comparable<T>, N extends AbstractListNode<T, N>> {
    protected N head;
    protected N tail;
    protected int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(T data) {
        N abstractListNode = createNode(data);
        this.head = abstractListNode;
        this.tail = abstractListNode;
        this.size = 1;
    }

    public LinkedList(T[] dataArray) {
        for (T data : dataArray) {
            add(data);
        }
    }

    protected abstract N createNode(T data);

    protected abstract N getNodeAtIndex(int index);

    public abstract void add(T data);

    public abstract void addToHead(T data);

    public abstract void addAtIndex(int index, T data) throws IndexOutOfBoundsException;

    public abstract T deleteHead();

    public abstract T deleteTail();

    public abstract T deleteAtIndex(int index) throws IndexOutOfBoundsException;

    public abstract void reverseLinkedList();

    public abstract void reverseInGroups(int k);

    protected abstract N mergeTwoSortedLists(N left, N right);

    public abstract void removeDuplicates();

    public abstract void generateCyclicList(int n, int cycleIndex, Function<Integer, T> generator);

    protected N findTheMiddleNodeData(N node) {
        if (node == null) return null;

        N slow = node;
        N fast = node.getNext();

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return slow;
    }

    public void sort() {
        head = mergeSort(head);

        tail = head;
        while (tail != null && tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

    private N mergeSort(N node) {
        // Base case
        if (node == null || node.getNext() == null) return node;

        N middle = findTheMiddleNodeData(node);
        N nextOfMiddle = middle.getNext();
        middle.setNext(null);

        N left = mergeSort(node);
        N right = mergeSort(nextOfMiddle);

        return mergeTwoSortedLists(left, right);
    }

    public Integer getNodeIndexByData(T data) {
        N node = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.getData(), data)) {
                return i;
            }
            node = node.getNext();
        }
        return null; // not found
    }

    public boolean hasCycle() {
        if (head == null || head.getNext() == null) {
            return false;
        }

        N slow = head;
        N fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();           // Move 1 step
            fast = fast.getNext().getNext();      // Move 2 steps

            if (slow == fast) {
                return true;            // Cycle detected
            }
        }

        return false;                   // No cycle
    }

    public T detectStartOfCycle() {
        if (head == null || head.getNext() == null) {
            return null;
        }

        N slow = head;
        N fast = head;
        N start = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();           // Move 1 step
            fast = fast.getNext().getNext();      // Move 2 steps

            if (slow == fast) {
                while (start != slow) {
                    start = start.getNext();
                    slow = slow.getNext();
                }
                return start.getData();
            }
        }

        return null;
    }

    public void swap(int index1, int index2) {
        if (index1 == index2) return;

        // Validate indices
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Invalid indices: " + index1 + ", " + index2);
        }

        // Get nodes at both indices
        N node1 = getNodeAtIndex(index1);
        N node2 = getNodeAtIndex(index2);

        // Swap the data
        T tempData = node1.getData();
        node1.setData(node2.getData());
        node2.setData(tempData);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        N current = head;
        Set<N> visited = new HashSet<>();

        while (current != null) {
            // Detect cycle
            if (visited.contains(current)) {
                System.out.print(current.getData() + " -> (cycle detected here)");
                break;
            }

            System.out.print(current.getData() + " -> ");
            visited.add(current);
            current = current.getNext();
        }

        if (current == null) {
            System.out.println("null");
        } else {
            System.out.println(); // cycle case already handled above
        }

        System.out.println("List size (logical count): " + getSize());
    }

    public boolean isEqual(LinkedList<T, N> other) {
        if (this.size != other.size) {
            return false;
        }

        N current1 = this.head;
        N current2 = other.head;

        while (current1 != null && current2 != null) {
            if (current1.getData() != current2.getData()) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LinkedList<?, ?> other = (LinkedList<?, ?>) obj;

        N current1 = this.head;
        AbstractListNode<?, ?> current2 = other.head;

        while (current1 != null && current2 != null) {
            if (!Objects.equals(current1.getData(), current2.getData())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return current1 == null && current2 == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        N current = head;

        while (current != null) {
            result = 31 * result + (current.getData() != null ? current.getData().hashCode() : 0);
            current = current.getNext();
        }

        return result;
    }
}
