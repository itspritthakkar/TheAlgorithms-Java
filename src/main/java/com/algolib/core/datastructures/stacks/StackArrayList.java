package com.algolib.core.datastructures.stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class StackArrayList<T> implements Stack<T> {

    private final ArrayList<T> stack;

    public StackArrayList() {
        this.stack = new ArrayList<>();
    }

    public StackArrayList(T data) {
        this();
        push(data);
    }

    public StackArrayList(T[] dataArray) {
        this();
        for (T data : dataArray) {
            push(data);
        }
    }

    @Override
    public void push(T data) {
        stack.add(data);
    }

    @Override
    public T pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return stack.removeLast();
    }

    @Override
    public T peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return stack.getLast();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public boolean contains(T item) {
        return stack.contains(item);
    }

    @Override
    public int search(T item) {
        return stack.indexOf(item);
    }

    @Override
    public void reverse() {
        Collections.reverse(stack);
    }

    public Stack<T> reverseCopy() {
        StackArrayList<T> copy = new StackArrayList<>();
        copy.stack.addAll(stack);
        Collections.reverse(copy.stack);
        return copy;
    }

    @Override
    public Stack<T> copy() {
        StackArrayList<T> copy = new StackArrayList<>();
        copy.stack.addAll(stack);
        return copy;
    }

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
