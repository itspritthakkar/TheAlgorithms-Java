package com.algolib.core.datastructures.stacks;

import com.algolib.utils.helpers.ArrayHelper;

import java.util.Arrays;
import java.util.Objects;

/**
 * A stack implementation based on an array.
 *
 * <p>Supports standard stack operations including push, pop, peek, reverse,
 * and search. Additional utilities like deep copy and conversion to string
 * are also supported.
 *
 * @param <T> the type of elements in this stack
 */
public class StackArray<T> implements Stack<T> {

    /** The default capacity of the stack */
    private static final int DEFAULT_CAPACITY = 10;

    /** The maximum number of elements in the stack. */
    private int maxSize;

    /** Array to store stack elements */
    private T[] stackArray;

    /** The top node of the stack. */
    private int top;

    /** Constructs an empty stack. */
    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a stack with a single initial element.
     *
     * @param data the initial element to push
     */
    public StackArray(T data) {
        this(DEFAULT_CAPACITY);
        push(data);
    }

    /**
     * Constructs an empty stack of a particular size.
     *
     * @param size the size of the stack
     */
    @SuppressWarnings("unchecked")
    public StackArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be greater than 0");
        }
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
        this.top = -1;
    }

    /**
     * Constructs a stack with a single initial element of a particular size.
     *
     * @param data the initial element to push
     * @param size the size of the stack
     */
    @SuppressWarnings("unchecked")
    public StackArray(T data, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be greater than 0");
        }
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
        push(data);
        this.top = -1;
    }

    /**
     * Constructs a stack by pushing all elements from the given array.
     *
     * @param dataArray the array of elements to push
     */
    public StackArray(T[] dataArray) {
        this(DEFAULT_CAPACITY);
        for(T data : dataArray) {
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
        if (isFull()) {
            resize(maxSize * 2);
        }
        stackArray[++top] = data;
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
            throw new IllegalStateException("Stack is empty");
        }
        T item = stackArray[top];
        stackArray[top--] = null;
        return item;
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
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return top + 1;
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(T item) {
        for (int i = 0; i <= top; i++) {
            if (Objects.equals(stackArray[i], item)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        Arrays.fill(stackArray, 0, top + 1, null);
        top = -1;
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int search(T item) {
        for (int i = top; i >= 0; i--) {
            if (Objects.equals(stackArray[i], item)) {
                return top - i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void reverse() {
        int left = 0, right = top;
        while (left < right) {
            ArrayHelper.swapElements(stackArray, left, right);
            left++;
            right--;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stack<T> reverseCopy() {
        StackArray<T> reversed = new StackArray<>(maxSize);
        for (int i = 0; i <= top; i++) {
            reversed.stackArray[i] = stackArray[top - i];
        }
        reversed.top = top;
        return reversed;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public Stack<T> copy() {
        StackArray<T> copied = new StackArray<>(maxSize);
        if (top + 1 >= 0) System.arraycopy(stackArray, 0, copied.stackArray, 0, top + 1);
        copied.top = top;
        return copied;
    }

    /** Checks if the stack is full */
    public boolean isFull() {
        return top + 1 == maxSize;
    }

    /**
     * Resize the stack
     *
     * @param newSize New size to set for the stack array
     */
    private void resize(int newSize) {
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(stackArray, 0, newArray, 0, top + 1);
        stackArray = newArray;
        maxSize = newSize;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(stackArray[i]);
            if (i < top) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StackArray<?> other = (StackArray<?>) obj;

        if (this.size() != other.size()) return false;

        int currentIndex = 0;

        while (currentIndex < this.size()) {
            if (!Objects.equals(this.stackArray[currentIndex], other.stackArray[currentIndex])) {
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
            hash = 31 * hash + Objects.hashCode(this.stackArray[currentIndex]);
            currentIndex++;
        }

        return hash;
    }
}
