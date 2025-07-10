package com.algolib.core.datastructures.stacks;

public interface Stack<T> {

    void push(T value);

    T pop() throws IllegalStateException;

    T peek() throws IllegalStateException;

    boolean isEmpty();

    int size();

    void clear();

    boolean contains(T item);

    int search(T item);

    void reverse();

    Stack<T> reverseCopy();

    Stack<T> copy();

    String toString();
}
