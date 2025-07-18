package com.algolib.core.datastructures.lists;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * A concrete implementation of a singly linked list.
 *
 * <p>Extends the abstract {@link LinkedList} class using {@link SinglyListNode}
 * for node structure. Provides full support for addition, deletion, reversal,
 * deduplication, cycle generation, and more.
 *
 * @param <T> the type of data stored in the list
 *
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public class SinglyLinkedList<T extends Comparable<T>> extends LinkedList<T, SinglyListNode<T>> {

    /**
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
        super();
    }

    /**
     * Constructs a singly linked list with a single element.
     *
     * @param data the data to initialize the list with
     */
    public SinglyLinkedList(T data) {
        super(data);
    }

    /**
     * Constructs a singly linked list from an array of data.
     *
     * @param dataArray the elements to populate the list
     */
    public SinglyLinkedList(T[] dataArray) {
        super(dataArray);
    }

    /** {@inheritDoc} */
    @Override
    protected SinglyListNode<T> createNode(T data) {
        return new SinglyListNode<>(data);
    }

    /**
     * Returns the head node of the list.
     */
    public SinglyListNode<T> getHead() {
        return head;
    }

    /**
     * Returns the tail node of the list.
     */
    public SinglyListNode<T> getTail() {
        return tail;
    }

    /**
     * {@inheritDoc}
     *
     * @param index {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    protected SinglyListNode<T> getNodeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        SinglyListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Retrieves the node immediately before the specified index.
     *
     * <p>This is useful for operations like insertion or deletion where access
     * to the previous node is required. For example, to insert at index {@code i},
     * you typically need to link from {@code i - 1}.
     *
     * @param index the index whose previous node is needed (must be > 0 and < size)
     * @return the node at position {@code index - 1}
     * @throws IndexOutOfBoundsException if index is ≤ 0 or ≥ size
     */
    private SinglyListNode<T> getPrevNodeAtIndex(int index) {
        if (index <= 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for previous node: " + index);
        }

        return getNodeAtIndex(index - 1);
    }

    /**
     * Retrieves both the current node and its previous node at the specified index.
     *
     * <p>This helper is useful for delete or modify operations where both the
     * node and its predecessor are required in a single traversal step.
     *
     * @param index the index of the current node (must be ≥ 1)
     * @return a {@link NodePair} containing the previous and current nodes
     * @throws IndexOutOfBoundsException if {@code index - 1} is invalid
     */
    private NodePair<T> getCurrentAndPrevNodeAtIndex(int index) {
        SinglyListNode<T> prev = getPrevNodeAtIndex(index - 1);
        SinglyListNode<T> current = prev.getNext();

        return new NodePair<>(prev, current);
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Trying to add at an invalid index: " + index);
        }

        SinglyListNode<T> newNode = new SinglyListNode<>(data);

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            SinglyListNode<T> prev = getPrevNodeAtIndex(index);
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
        }

        size++;
    }

    /** {@inheritDoc} */
    public T deleteHead() {
        if (isEmpty()) return null;

        SinglyListNode<T> oldHead = head;
        head = head.getNext();
        oldHead.setNext(null);
        size--;

        if (isEmpty()) tail = null;

        return oldHead.getData();
    }

    /** {@inheritDoc} */
    public T deleteTail() {
        if (isEmpty()) return null;

        SinglyListNode<T> oldTail = tail;

        if (head == tail) {
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

    /** {@inheritDoc} */
    public T deleteAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Trying to delete an invalid index: " + index);
        }

        if (index == 0) return deleteHead();
        if (index == size - 1) return deleteTail();

        NodePair<T> nodePair = getCurrentAndPrevNodeAtIndex(index);
        SinglyListNode<T> prev = nodePair.getPrev();
        SinglyListNode<T> nodeToDelete = nodePair.getCurrent();

        prev.setNext(nodeToDelete.getNext());
        nodeToDelete.setNext(null);
        size--;

        return nodeToDelete.getData();
    }

    /** {@inheritDoc} */
    public void clear() {
        SinglyListNode<T> current = head;
        while (current != null) {
            SinglyListNode<T> next = current.getNext();
            current.setNext(null);
            current = next;
        }

        head = tail = null;
        size = 0;
    }

    /** {@inheritDoc} */
    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> cloned = new SinglyLinkedList<>();
        SinglyListNode<T> current = head;

        while (current != null) {
            cloned.add(current.getData());
            current = current.getNext();
        }

        return cloned;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        if (isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        SinglyListNode<T> current = head;

        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(" -> ");
            current = current.getNext();
        }

        return sb.toString();
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    public void reverseInGroups(int k) {
        if (head == null || k <= 1) return;
        head = reverseGroupHelper(head, k);
    }

    private SinglyListNode<T> reverseGroupHelper(SinglyListNode<T> node, int k) {
        SinglyListNode<T> curr = node;
        int count = 0;

        while (curr != null && count < k) {
            curr = curr.getNext();
            count++;
        }

        if (count < k) return node;

        SinglyListNode<T> prev = null;
        curr = node;

        for (int i = 0; i < k; i++) {
            SinglyListNode<T> next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        node.setNext(reverseGroupHelper(curr, k));
        return prev;
    }

    /** {@inheritDoc} */
    protected SinglyListNode<T> mergeTwoSortedLists(SinglyListNode<T> left, SinglyListNode<T> right) {
        SinglyListNode<T> dummy = new SinglyListNode<>(null);
        SinglyListNode<T> tail = dummy;

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

        tail.setNext((left != null) ? left : right);
        return dummy.getNext();
    }

    /** {@inheritDoc} */
    public void removeDuplicates() {
        if (head == null) return;

        Set<T> seen = new HashSet<>();
        SinglyListNode<T> current = head;
        seen.add(current.getData());

        while (current.getNext() != null) {
            if (seen.contains(current.getNext().getData())) {
                current.setNext(current.getNext().getNext());
                size--;
            } else {
                seen.add(current.getNext().getData());
                current = current.getNext();
            }
        }

        tail = current;
    }

    /** {@inheritDoc} */
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

    /**
     * Utility class to represent a pair of nodes: previous and current.
     */
    private static class NodePair<T> {
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
