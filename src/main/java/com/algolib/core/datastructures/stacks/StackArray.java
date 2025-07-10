package com.algolib.core.datastructures.stacks;

import com.algolib.utils.helpers.ArrayHelper;

import java.util.Arrays;
import java.util.Objects;

public class StackArray<T> implements Stack<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int maxSize;
    private T[] stackArray;
    private int top;

    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    public StackArray(T data) {
        this(DEFAULT_CAPACITY);
        push(data);
    }

    @SuppressWarnings("unchecked")
    public StackArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Stack size must be greater than 0");
        }
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
        this.top = -1;
    }

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

    public StackArray(T[] dataArray) {
        this(DEFAULT_CAPACITY);
        for(T data : dataArray) {
            push(data);
        }
    }

    @Override
    public void push(T data) {
        if (isFull()) {
            resize(maxSize * 2);
        }
        stackArray[++top] = data;
    }

    @Override
    public T pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T item = stackArray[top];
        stackArray[top--] = null;
        return item;
    }

    @Override
    public T peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stackArray[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void clear() {
        Arrays.fill(stackArray, 0, top + 1, null);
        top = -1;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i <= top; i++) {
            if (Objects.equals(stackArray[i], item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int search(T item) {
        for (int i = top; i >= 0; i--) {
            if (Objects.equals(stackArray[i], item)) {
                return top - i;
            }
        }
        return -1;
    }

    @Override
    public void reverse() {
        int left = 0, right = top;
        while (left < right) {
            ArrayHelper.swapElements(stackArray, left, right);
            left++;
            right--;
        }
    }

    @Override
    public Stack<T> reverseCopy() {
        StackArray<T> reversed = new StackArray<>(maxSize);
        for (int i = 0; i <= top; i++) {
            reversed.stackArray[i] = stackArray[top - i];
        }
        reversed.top = top;
        return reversed;
    }

    @Override
    public Stack<T> copy() {
        StackArray<T> copied = new StackArray<>(maxSize);
        if (top + 1 >= 0) System.arraycopy(stackArray, 0, copied.stackArray, 0, top + 1);
        copied.top = top;
        return copied;
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    private void resize(int newSize) {
        @SuppressWarnings("unchecked") T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(stackArray, 0, newArray, 0, top + 1);
        stackArray = newArray;
        maxSize = newSize;
    }

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
