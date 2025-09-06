package com.algolib.core.datastructures.stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * A stack implementation based on an array list.
 *
 * <p>Supports standard stack operations including push, pop, peek, reverse,
 * and search. Additional utilities like deep copy and conversion to string
 * are also supported.
 *
 * @param <T> the type of elements in this stack
 */
public class StackArrayList<T> implements Stack<T> {

    /** Array list to store stack elements */
    private final ArrayList<T> stack;

    /** Constructs an empty stack. */
    public StackArrayList() {
        this.stack = new ArrayList<>();
    }

    /**
     * Constructs a stack by pushing all elements from the given array.
     *
     * @param dataArray the array of elements to push
     */
    public StackArrayList(T[] dataArray) {
        this();
        for (T data : dataArray) {
            push(data);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param data {@inheritDoc}
     */
    @Override
    public void push(T data) {
        stack.add(data);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public T pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return stack.removeLast();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public T peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return stack.getLast();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return stack.size();
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(T item) {
        return stack.contains(item);
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        stack.clear();
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int search(T item) {
        return stack.indexOf(item);
    }

    /** {@inheritDoc} */
    @Override
    public void reverse() {
        Collections.reverse(stack);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    public Stack<T> reverseCopy() {
        StackArrayList<T> copy = new StackArrayList<>();
        copy.stack.addAll(stack);
        Collections.reverse(copy.stack);
        return copy;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stack<T> copy() {
        StackArrayList<T> copy = new StackArrayList<>();
        copy.stack.addAll(stack);
        return copy;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StackArrayList<?> other = (StackArrayList<?>) obj;

        if (this.size() != other.size()) return false;

        int currentIndex = 0;

        while (currentIndex < this.size()) {
            if (!Objects.equals(this.stack.get(currentIndex), other.stack.get(currentIndex))) {
                return false;
            }
            currentIndex++;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int currentIndex = 0;

        while (currentIndex < this.size()) {
            hash = 31 * hash + Objects.hashCode(this.stack.get(currentIndex));
            currentIndex++;
        }

        return hash;
    }
}
