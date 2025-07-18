package com.algolib.core.datastructures.lists;

/**
 * Abstract base class representing a node in a linked list.
 *
 * <p>This class stores generic data along with a reference to the next node.
 * It is meant to be extended by concrete list node implementations.
 *
 * @param <T> the type of data stored in the node
 * @param <N> the type of node that extends AbstractListNode
 *            (used for recursive generic typing to maintain type safety)
 * @author Prit Thakkar (pritthakkar111101@gmail.com)
 */
public abstract class AbstractListNode<T, N extends AbstractListNode<T, N>> {

    /**
     * The data stored in this node.
     */
    private T data;

    /**
     * Reference to the next node in the list.
     */
    private N next;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in this node
     */
    public AbstractListNode(T data) {
        this.data = data;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data for this node.
     *
     * @param data the new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the next node.
     *
     * @return the next node
     */
    public N getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     *
     * @param next the next node
     */
    public void setNext(N next) {
        this.next = next;
    }

    /**
     * Determines whether this node is equal to another object.
     *
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Returns the hash code of this node.
     *
     * @return the hash code
     */
    @Override
    public abstract int hashCode();
}
