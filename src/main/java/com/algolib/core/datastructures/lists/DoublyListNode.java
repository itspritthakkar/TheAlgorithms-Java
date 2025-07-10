package com.algolib.core.datastructures.lists;

import java.util.Objects;

public class DoublyListNode<T> extends AbstractListNode<T, DoublyListNode<T>> {

    private DoublyListNode<T> prev;

    public DoublyListNode(T data) {
        super(data);
    }

    public DoublyListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoublyListNode<T> prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        DoublyListNode<?> other = (DoublyListNode<?>) obj;

        // Compare the data
        return Objects.equals(this.getData(), other.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
