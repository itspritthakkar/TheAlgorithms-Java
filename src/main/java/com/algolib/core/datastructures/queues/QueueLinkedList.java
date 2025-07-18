package com.algolib.core.datastructures.queues;

import com.algolib.core.datastructures.lists.SinglyLinkedList;

import java.lang.IllegalStateException;

/**
 * A queue implemented using composition with a singly linked list.
 * This queue follows the FIFO (First-In-First-Out) principle.
 *
 * @param <T> the type of elements held in this queue
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public class QueueLinkedList<T extends Comparable<T>> implements Queue<T> {

    private SinglyLinkedList<T> list;

    /**
     * Constructs an empty queue.
     */
    public QueueLinkedList() {
        this.list = new SinglyLinkedList<>();
    }

    /**
     * Inserts the specified element at the rear of the queue.
     *
     * @param item the element to add
     */
    @Override
    public void enqueue(T item) {
        list.add(item); // Add to tail
    }

    /**
     * Inserts the specified element into this queue if possible.
     * Always returns true for unbounded queue.
     *
     * @param item the element to add
     * @return {@code true} (since list is unbounded)
     */
    public boolean offer(T item) {
        enqueue(item);
        return true;
    }

    /**
     * Retrieves and removes the front element of the queue.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.deleteHead(); // Remove from head
    }

    /**
     * Retrieves and removes the front of this queue, or returns {@code null} if the queue is empty.
     *
     * @return the front element or {@code null} if queue is empty
     */
    public T poll() {
        if (isEmpty()) return null;
        return dequeue();
    }

    /**
     * Retrieves, but does not remove, the front element of the queue.
     *
     * @return the front element
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getHead().getData(); // or list.peek() if you add such a method
    }

    /**
     * Returns {@code true} if the queue contains no elements.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return list.getSize();
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Returns {@code true} if the queue contains the specified element.
     *
     * @param item the element to search for
     * @return {@code true} if the item is present; {@code false} otherwise
     */
    @Override
    public boolean contains(T item) {
        return list.getNodeIndexByData(item) != null;
    }

    /**
     * Returns the position of the specified element in the queue.
     *
     * @param item the element to search for
     * @return the zero-based index of the item, or -1 if not found
     */
    @Override
    public int search(T item) {
        return list.getNodeIndexByData(item);
    }

    /**
     * Reverses the order of the queue in place.
     */
    @Override
    public void reverse() {
        list.reverseLinkedList();
    }

    /**
     * Returns a new queue that is a reversed copy of this queue.
     *
     * @return a new reversed queue
     */
    @Override
    public Queue<T> reverseCopy() {
        QueueLinkedList<T> clone = new QueueLinkedList<>();
        clone.list = list.copy();
        clone.list.reverseLinkedList();

        return clone;
    }

    /**
     * Returns a new queue that is a shallow copy of this queue.
     *
     * @return a new queue containing the same elements in the same order
     */
    @Override
    public Queue<T> copy() {
        QueueLinkedList<T> clone = new QueueLinkedList<>();
        clone.list = list.copy();

        return clone;
    }

    /**
     * Returns a string representation of the queue, from front to rear.
     *
     * @return a string displaying the queue elements
     */
    @Override
    public String toString() {
        return list.toString();
    }
}