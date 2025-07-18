package com.algolib.core.datastructures.lists;

import java.util.Objects;

/**
 * A concrete implementation of a singly linked list node.
 *
 * <p>This class extends {@link AbstractListNode} to represent nodes in
 * a singly linked list structure, where each node contains data and a
 * reference to the next node.
 *
 * @param <T> the type of data stored in the node
 *
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public class SinglyListNode<T> extends AbstractListNode<T, SinglyListNode<T>> {

    /**
     * Constructs a new singly linked list node with the specified data.
     *
     * @param data the data to store in the node
     */
    public SinglyListNode(T data) {
        super(data);
    }

    /**
     * Compares this node with another object for equality.
     * Two nodes are equal if they are of the same type, and
     * their data and next references are equal.
     *
     * @param obj the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        SinglyListNode<?> other = (SinglyListNode<?>) obj;

        // Compare data and next node
        return Objects.equals(this.getData(), other.getData()) &&
                Objects.equals(this.getNext(), other.getNext());
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