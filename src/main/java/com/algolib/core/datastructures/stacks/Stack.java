package com.algolib.core.datastructures.stacks;

/**
 * Generic Stack interface defining core stack operations.
 *
 * @param <T> the type of elements held in this stack
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public interface Stack<T> {

    /**
     * Pushes an item onto the top of the stack.
     *
     * @param value the item to be pushed onto the stack
     */
    void push(T value);

    /**
     * Removes and returns the top item from the stack.
     *
     * @return the item removed from the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    T pop() throws IllegalStateException;

    /**
     * Retrieves, but does not remove, the top item of the stack.
     *
     * @return the top item of the stack
     * @throws IllegalStateException if the stack is empty
     */
    T peek() throws IllegalStateException;

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack has no elements, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the current size of the stack
     */
    int size();

    /**
     * Removes all elements from the stack.
     */
    void clear();

    /**
     * Checks if the stack contains the specified item.
     *
     * @param item the item to search for
     * @return {@code true} if the item exists in the stack, {@code false} otherwise
     */
    boolean contains(T item);

    /**
     * Searches for the item in the stack and returns its 0-based position
     * from the top. Returns -1 if not found.
     *
     * @param item the item to search for
     * @return the 0-based index from the top of the stack, or -1 if not found
     */
    int search(T item);

    /**
     * Reverses the order of elements in the stack in-place.
     */
    void reverse();

    /**
     * Returns a new stack that contains the same elements in reverse order.
     * The original stack remains unchanged.
     *
     * @return a new stack with elements in reverse order
     */
    Stack<T> reverseCopy();

    /**
     * Returns a shallow copy of the current stack.
     *
     * @return a new stack containing the same elements in the same order
     */
    Stack<T> copy();

    /**
     * Returns a string representation of the stack contents.
     *
     * @return a string showing elements from top to bottom
     */
    @Override
    String toString();
}
