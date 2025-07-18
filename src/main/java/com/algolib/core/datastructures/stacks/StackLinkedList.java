package com.algolib.core.datastructures.stacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;

/**
 * A stack implementation based on a singly linked list.
 *
 * <p>Supports standard stack operations including push, pop, peek, reverse,
 * and search. Additional utilities like deep copy and conversion to array
 * are also supported.
 *
 * @param <T> the type of elements in this stack
 */
public class StackLinkedList<T> implements Stack<T> {

    /** The top node of the stack. */
    protected StackNode<T> top;

    /** The number of elements in the stack. */
    protected int size;

    /** Constructs an empty stack. */
    public StackLinkedList() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Constructs a stack with a single initial element.
     *
     * @param data the initial element to push
     */
    public StackLinkedList(T data) {
        this.top = new StackNode<>(data);
        this.size = 1;
    }

    /**
     * Constructs a stack by pushing all elements from the given array.
     *
     * @param dataArray the array of elements to push
     */
    public StackLinkedList(T[] dataArray) {
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
        StackNode<T> newNode = new StackNode<>(data);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public T pop() throws IllegalStateException {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }

        StackNode<T> oldTop = top;
        top = oldTop.getNext();
        oldTop.setNext(null);
        size--;
        return oldTop.getData();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public T peek() throws IllegalStateException {
        if (top == null) {
            throw new IllegalStateException("Stack is empty");
        }

        return top.getData();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return top == null || size == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(T item) {
        StackNode<T> current = top;

        while(current != null) {
            if(Objects.equals(current.getData(), item)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        StackNode<T> current = top;

        while(current != null) {
            StackNode<T> tempNext = current.getNext();
            current.setNext(null);
            current = tempNext;
        }
        top = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param item {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int search(T item) {
        StackNode<T> current = top;

        int currentIndex = 0;
        while(current != null) {
            if(Objects.equals(current.getData(), item)) {
                return currentIndex;
            }
            current = current.getNext();
            currentIndex++;
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void reverse() {
        StackNode<T> current = top;
        StackNode<T> prev = null;

        while(current != null) {
            StackNode<T> tempNext = current.getNext();
            current.setNext(prev);
            prev = current;
            current = tempNext;
        }

        top = prev;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public StackLinkedList<T> reverseCopy() {
        StackLinkedList<T> reversed = new StackLinkedList<>();
        StackNode<T> current = top;

        while (current != null) {
            reversed.push(current.getData()); // Reversed order
            current = current.getNext();
        }

        return reversed;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public StackLinkedList<T> copy() {
        StackLinkedList<T> reversed = new StackLinkedList<>();
        StackNode<T> current = top;

        while (current != null) {
            reversed.push(current.getData()); // Reversed order
            current = current.getNext();
        }

        reversed.reverse();

        return reversed;
    }

    public T[] toArray(IntFunction<T[]> generator) {
        List<T> temp = new ArrayList<>();
        StackNode<T> current = top;
        while (current != null) {
            temp.add(current.getData());
            current = current.getNext();
        }
        Collections.reverse(temp);
        return temp.toArray(generator);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        if (isEmpty()) return null;

        return buildStringFromBottom(top);
    }

    private String buildStringFromBottom(StackNode<T> node) {
        if (node.getNext() == null) {
            return String.valueOf(node.getData());
        }
        return buildStringFromBottom(node.getNext()) + " -> " + node.getData();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        StackLinkedList<?> other = (StackLinkedList<?>) obj;

        if (this.size != other.size) return false;

        StackNode<T> current1 = this.top;
        StackNode<?> current2 = other.top;

        while (current1 != null && current2 != null) {
            if (!Objects.equals(current1.getData(), current2.getData())) {
                return false;
            }
            current1 = current1.getNext();
            current2 = current2.getNext();
        }

        return current1 == null && current2 == null;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        StackNode<T> current = top;

        while (current != null) {
            hash = 31 * hash + Objects.hashCode(current.getData());
            current = current.getNext();
        }

        return hash;
    }
}
