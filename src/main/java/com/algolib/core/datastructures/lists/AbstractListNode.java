package com.algolib.core.datastructures.lists;

public abstract class AbstractListNode<T, N extends AbstractListNode<T, N>> {
    private T data;
    private N next;

    public AbstractListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public N getNext() {
        return next;
    }

    public void setNext(N next) {
        this.next = next;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
