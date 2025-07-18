package com.algolib.core.datastructures.queues;

/**
 * A generic Queue interface that defines the standard operations
 * for a First-In-First-Out (FIFO) data structure.
 *
 * @param <T> the type of elements held in this queue
 *
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public interface Queue<T> {

    /**
     * Adds an element to the back of the queue.
     *
     * @param item the element to be added
     */
    void enqueue(T item);

    /**
     * Inserts the specified element into this queue if possible.
     * Always returns true for unbounded queue.
     *
     * @param item the element to add
     * @return {@code true} (since list is unbounded)
     */
    boolean offer(T item);

    /**
     * Removes and returns the front element of the queue.
     *
     * @return the front element
     * @throws IllegalStateException if the queue is empty
     */
    T dequeue();

    /**
     * Retrieves and removes the front of this queue, or returns {@code null} if the queue is empty.
     *
     * @return the front element or {@code null} if queue is empty
     */
    T poll();

    /**
     * Returns the front element without removing it from the queue.
     *
     * @return the front element
     * @throws IllegalStateException if the queue is empty
     */
    T peek();

    /**
     * Checks whether the queue is empty.
     *
     * @return {@code true} if the queue is empty; {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements currently in the queue.
     *
     * @return the size of the queue
     */
    int size();

    /**
     * Removes all elements from the queue.
     */
    void clear();

    /**
     * Checks if the queue contains the specified item.
     *
     * @param item the item to search for
     * @return {@code true} if the item exists in the queue, {@code false} otherwise
     */
    boolean contains(T item);

    /**
     * Searches for the item in the queue and returns its 0-based position
     * from the top. Returns -1 if not found.
     *
     * @param item the item to search for
     * @return the 0-based index from the top of the queue, or -1 if not found
     */
    int search(T item);

    /**
     * Reverses the order of elements in the queue in-place.
     */
    void reverse();

    /**
     * Returns a new queue that contains the same elements in reverse order.
     * The original queue remains unchanged.
     *
     * @return a new queue with elements in reverse order
     */
    Queue<T> reverseCopy();

    /**
     * Returns a shallow copy of the current queue.
     *
     * @return a new queue containing the same elements in the same order
     */
    Queue<T> copy();

    /**
     * Returns a string representation of the queue contents.
     *
     * @return a string showing elements from top to bottom
     */
    @Override
    String toString();
}
