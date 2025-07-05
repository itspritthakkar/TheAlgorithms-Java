package com.algolib.core.datastructures.lists;

import java.util.Objects;

public class SinglyListNode<T> extends AbstractListNode<T, SinglyListNode<T>>{
    public SinglyListNode(T data) {
        super(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        SinglyListNode<?> other = (SinglyListNode<?>) obj;

        // Compare the data
        return Objects.equals(this.getData(), other.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
