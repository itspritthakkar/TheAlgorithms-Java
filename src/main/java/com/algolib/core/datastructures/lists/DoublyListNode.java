package com.algolib.core.datastructures.lists;

import java.util.Objects;

/**
 * A concrete implementation of a doubly linked list node.
 *
 * <p>This class extends {@link AbstractListNode} to represent nodes in
 * a doubly linked list structure, where each node contains data and a
 * reference to the next & previous node.
 *
 * @param <T> the type of data stored in the node
 *
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public class DoublyListNode<T> extends AbstractListNode<T, DoublyListNode<T>> {

    /**
     * Reference to the previous node in the list.
     */
    private DoublyListNode<T> prev;

    /**
     * Constructs a new doubly linked list node with the specified data.
     *
     * @param data the data to store in the node
     */
    public DoublyListNode(T data) {
        super(data);
    }

    /**
     * Returns the previous node.
     *
     * @return the previous node
     */
    public DoublyListNode<T> getPrev() {
        return prev;
    }

    /**
     * Sets the reference to the previous node.
     *
     * @param prev the previous node
     */
    public void setPrev(DoublyListNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Compares this node with another object for equality.
     * Two nodes are equal if they are of the same type, and
     * their data, next and previous references are equal.
     *
     * @param obj the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        DoublyListNode<?> other = (DoublyListNode<?>) obj;

        // Compare the data
        return Objects.equals(this.getData(), other.getData()) &&
                Objects.equals(this.getNext(), other.getNext()) &&
                Objects.equals(this.getPrev(), other.getPrev());
    }

    /**
     * Computes the hash code for this node based on its data.
     *
     * @return the hash code of the node
     */
    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
