package com.algolib.core.datastructures.queues;

import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * A fixed-size circular queue implemented using an array.
 *
 * @param <T> the type of elements in the queue
 *
 * Author: Prit Thakkar (pritthakkar111101@gmail.com)
 */
public class QueueCircularArray<T> implements Queue<T> {

    private T[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    /**
     * Constructs a CircularArrayQueue with the specified capacity.
     *
     * @param capacity the maximum number of elements this queue can hold
     */
    @SuppressWarnings("unchecked")
    public QueueCircularArray(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    /**
     * Inserts the specified element at the rear of the queue.
     *
     * @param item the element to add
     * @throws IllegalStateException if the queue is full
     */
    @Override
    public void enqueue(T item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        array[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
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
     * Removes and returns the front element of the queue.
     *
     * @return the front element
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = array[front];
        array[front] = null; // Help GC
        front = (front + 1) % capacity;
        size--;
        return item;
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
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return array[front];
    }

    /**
     * Returns {@code true} if the queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return the current size of the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        Arrays.fill(array, null);
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * Returns {@code true} if the queue contains the specified element.
     *
     * @param item the element to search for
     * @return {@code true} if the item is present; {@code false} otherwise
     */
    @Override
    public boolean contains(T item) {
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity) {
            if (array[idx] != null && array[idx].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for the given item and returns its index relative to the front of the queue.
     *
     * @param item the item to search for
     * @return the index from front (0-based), or -1 if not found
     */
    @Override
    public int search(T item) {
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity) {
            if (array[idx] != null && array[idx].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Reverses the order of elements in the queue in place.
     */
    @Override
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            int left = (front + i) % capacity;
            int right = (front + size - 1 - i + capacity) % capacity;
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
    }

    /**
     * Returns a new queue that is a reversed copy of this queue.
     *
     * @return a new reversed queue
     */
    @Override
    public Queue<T> reverseCopy() {
        QueueCircularArray<T> copy = copyInternal();
        copy.reverse();
        return copy;
    }

    /**
     * Returns a shallow copy of this queue.
     *
     * @return a new queue containing the same elements in the same order
     */
    @Override
    public Queue<T> copy() {
        return copyInternal();
    }

    private QueueCircularArray<T> copyInternal() {
        QueueCircularArray<T> copy = new QueueCircularArray<>(capacity);
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity) {
            copy.enqueue(array[idx]);
        }
        return copy;
    }

    /**
     * Returns a string representation of the queue from front to rear.
     *
     * @return a string displaying the queue elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Front -> ");
        for (int i = 0, idx = front; i < size; i++, idx = (idx + 1) % capacity) {
            sb.append(array[idx]);
            if (i < size - 1) sb.append(" -> ");
        }
        sb.append(" -> Rear");
        return sb.toString();
    }
}
