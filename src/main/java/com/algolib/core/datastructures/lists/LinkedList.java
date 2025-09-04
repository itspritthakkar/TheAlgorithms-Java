package com.algolib.core.datastructures.lists;

import java.util.*;
import java.util.function.Function;

/**
 * An abstract implementation of a generic linked list.
 *
 * <p>This class defines the common logic and contract for linked lists, such as
 * sorting, detecting cycles, and swapping elements. Concrete implementations must
 * implement core methods like node creation, addition, deletion, and reversal.
 *
 * @param <T> the type of data stored in the list
 * @param <N> the type of node extending {@link AbstractListNode}
 *
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public abstract class LinkedList<T extends Comparable<T>, N extends AbstractListNode<T, N>> {

    /**
     * The head of the list.
     */
    protected N head;

    /**
     * The tail of the list.
     */
    protected N tail;

    /**
     * Logical size (number of nodes) of the list.
     */
    protected int size;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a list with a single node.
     *
     * @param data the initial node's data
     */
    public LinkedList(T data) {
        N abstractListNode = createNode(data);
        this.head = abstractListNode;
        this.tail = abstractListNode;
        this.size = 1;
    }

    /**
     * Constructs a list from an array of data.
     *
     * @param dataArray array of elements to add to the list
     */
    public LinkedList(T[] dataArray) {
        for (T data : dataArray) {
            add(data);
        }
    }

    /** Creates a new node instance. */
    protected abstract N createNode(T data);

    /**
     * Retrieves the node at the specified 0-based index.
     *
     * <p>This method starts traversal from the head of the list and walks forward
     * {@code index} times to reach the desired node.
     *
     * @param index the index of the node to retrieve (0-based)
     * @return the node at the specified index
     * @throws IndexOutOfBoundsException if the index is negative or exceeds the list bounds
     */
    protected abstract N getNodeAtIndex(int index) throws IndexOutOfBoundsException;

    /** Adds an element to the end of the list. */
    public abstract void add(T data);

    /** Adds an element to the head of the list. */
    public abstract void addToHead(T data);

    /** Inserts an element at a specified index. */
    public abstract void addAtIndex(int index, T data) throws IndexOutOfBoundsException;

    /** Deletes and returns the head element. */
    public abstract T deleteHead();

    /** Deletes and returns the tail element. */
    public abstract T deleteTail();

    /** Deletes and returns an element at a specified index. */
    public abstract T deleteAtIndex(int index) throws IndexOutOfBoundsException;

    /** Reverses the list in-place. */
    public abstract void reverse();

    /** Reverses the list in groups of k. */
    public abstract void reverseInGroups(int k);

    /** Merges two sorted sublists. */
    protected abstract N mergeTwoSortedLists(N left, N right);

    /** Removes duplicate elements from the list. */
    public abstract void removeDuplicates();

    /** Generates a cyclic list with given length and cycle entry point. */
    public abstract void generateCyclicList(int n, int cycleIndex, Function<Integer, T> generator);

    /** Clears the entire list. */
    public abstract void clear();

    /** Returns a deep copy of the list. */
    public abstract LinkedList<T,N> copy();

    /**
     * Finds the middle node in the list (or sublist).
     *
     * @param node the head of the sublist
     * @return the middle node
     */
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

    /**
     * Sorts the list using merge sort.
     */
    public void sort() {
        head = mergeSort(head);

        tail = head;
        while (tail != null && tail.getNext() != null) {
            tail = tail.getNext();
        }
    }

    /**
     * Internal recursive merge sort for linked lists.
     */
    private N mergeSort(N node) {
        if (node == null || node.getNext() == null) return node;

        N middle = findTheMiddleNodeData(node);
        N nextOfMiddle = middle.getNext();
        middle.setNext(null);

        N left = mergeSort(node);
        N right = mergeSort(nextOfMiddle);

        return mergeTwoSortedLists(left, right);
    }

    /**
     * Gets the index of the first node containing the specified data.
     *
     * @param data the data to search for
     * @return index or null if not found
     */
    public Integer getNodeIndexByData(T data) {
        N node = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.getData(), data)) {
                return i;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * Detects whether the list contains a cycle.
     *
     * @return true if a cycle exists, false otherwise
     */
    public boolean hasCycle() {
        if (head == null || head.getNext() == null) {
            return false;
        }

        N slow = head;
        N fast = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

            if (slow == fast) return true;
        }

        return false;
    }

    /**
     * Detects the start of the cycle, if any.
     *
     * @return the data at the cycle start, or null if no cycle
     */
    public T detectStartOfCycle() {
        if (head == null || head.getNext() == null) {
            return null;
        }

        N slow = head;
        N fast = head;
        N start = head;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();

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

    /**
     * Swaps the data of two nodes at the given indices.
     *
     * @param index1 the first index
     * @param index2 the second index
     * @throws IndexOutOfBoundsException if any index is invalid
     */
    public void swap(int index1, int index2) {
        if (index1 == index2) return;

        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Invalid indices: " + index1 + ", " + index2);
        }

        N node1 = getNodeAtIndex(index1);
        N node2 = getNodeAtIndex(index2);

        T tempData = node1.getData();
        node1.setData(node2.getData());
        node2.setData(tempData);
    }

    /**
     * Returns the number of elements in the list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks whether the list is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Prints the list contents to standard output.
     * Handles cycle detection and prints an appropriate message.
     */
    public void printList() {
        N current = head;
        Set<N> visited = new HashSet<>();

        while (current != null) {
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
        }

        System.out.println("List size (logical count): " + getSize());
    }

    /**
     * Compares this list to another for logical equality (same structure and data).
     */
    public boolean isEqual(LinkedList<T, N> other) {
        if (this.size != other.size) return false;

        N current1 = this.head;
        N current2 = other.head;

        while (current1 != null && current2 != null) {
            if (!Objects.equals(current1.getData(), current2.getData())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return true;
    }

    /**
     * Determines object equality with another list.
     */
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

    /**
     * Computes the hash code for the entire list.
     */
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

    /**
     * Converts the linked list into a {@link java.util.List}.
     *
     * <p>This method traverses the linked list from {@code head} to {@code tail}
     * and collects all elements in order into a new {@link java.util.ArrayList}.
     * The resulting list is independent of the linked list structure, so further
     * modifications to either do not affect the other.
     *
     * @return a {@link java.util.List} containing all elements of the linked list
     *         in their natural order
     */
    public List<T> toList() {
        List<T> result = new ArrayList<>(size);
        N current = head;
        while (current != null) {
            result.add(current.getData());
            current = current.getNext();
        }
        return result;
    }
}
