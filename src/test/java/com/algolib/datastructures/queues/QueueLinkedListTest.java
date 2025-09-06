package com.algolib.datastructures.queues;

import com.algolib.core.datastructures.queues.QueueLinkedList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueueLinkedListTest {

    @Test
    void enqueueAndDequeueWorks() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(1);
        q.enqueue(2);

        assertThat(q.size()).isEqualTo(2);
        assertThat(q.isEmpty()).isFalse();
        assertThat(q.dequeue()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.isEmpty()).isTrue();
    }

    @Test
    void offerAlwaysReturnsTrue() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        assertThat(q.offer(10)).isTrue();
        assertThat(q.size()).isEqualTo(1);
        assertThat(q.peek()).isEqualTo(10);
    }

    @Test
    void dequeueOnEmptyThrows() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        assertThatThrownBy(q::dequeue)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void pollReturnsNullOnEmpty() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        assertThat(q.poll()).isNull();
    }

    @Test
    void pollReturnsFrontAndRemovesIt() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(5);
        q.enqueue(6);

        assertThat(q.poll()).isEqualTo(5);
        assertThat(q.size()).isEqualTo(1);
    }

    @Test
    void peekReturnsFrontWithoutRemoving() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(42);

        assertThat(q.peek()).isEqualTo(42);
        assertThat(q.size()).isEqualTo(1);
    }

    @Test
    void peekOnEmptyThrows() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        assertThatThrownBy(q::peek)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void containsAndSearchWork() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(7);
        q.enqueue(8);

        assertThat(q.contains(7)).isTrue();
        assertThat(q.contains(100)).isFalse();

        assertThat(q.search(8)).isEqualTo(1);
        assertThat(q.search(100)).isEqualTo(-1);
    }

    @Test
    void clearEmptiesTheQueue() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(1);
        q.enqueue(2);

        q.clear();
        assertThat(q.isEmpty()).isTrue();
        assertThat(q.size()).isEqualTo(0);
    }

    @Test
    void reverseReversesTheQueue() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        q.reverse();

        assertThat(q.dequeue()).isEqualTo(3);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(1);
    }

    @Test
    void reverseCopyReturnsReversedClone() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        QueueLinkedList<Integer> reversed = (QueueLinkedList<Integer>) q.reverseCopy();

        assertThat(reversed.dequeue()).isEqualTo(3);
        assertThat(reversed.dequeue()).isEqualTo(2);
        assertThat(reversed.dequeue()).isEqualTo(1);

        // original unchanged
        assertThat(q.size()).isEqualTo(3);
    }

    @Test
    void copyReturnsShallowClone() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(10);
        q.enqueue(20);

        QueueLinkedList<Integer> copy = (QueueLinkedList<Integer>) q.copy();

        assertThat(copy.size()).isEqualTo(2);
        assertThat(copy.dequeue()).isEqualTo(10);
        assertThat(copy.dequeue()).isEqualTo(20);

        // original unchanged
        assertThat(q.size()).isEqualTo(2);
    }

    @Test
    void toStringWorks() {
        QueueLinkedList<Integer> q = new QueueLinkedList<>();
        q.enqueue(1);
        q.enqueue(2);
        assertThat(q.toString()).contains("1").contains("2");

        q.clear();
        assertThat(q.toString()).isEmpty();
    }
}
