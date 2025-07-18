package com.algolib.core.datastructures.stacks;

import com.algolib.core.datastructures.lists.AbstractListNode;

import java.util.Objects;

public class StackNode<T> extends AbstractListNode<T, StackNode<T>> {

    public StackNode(T data) {
        super(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        StackNode<?> other = (StackNode<?>) obj;

        // Compare the data
        return Objects.equals(this.getData(), other.getData()) &&
                Objects.equals(this.getNext(), other.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
