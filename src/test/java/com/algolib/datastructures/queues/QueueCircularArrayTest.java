package com.algolib.datastructures.queues;

import com.algolib.core.datastructures.queues.QueueCircularArray;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class QueueCircularArrayTest {

    @Test
    void enqueueAndDequeueWorks() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
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
        QueueCircularArray<Integer> q = new QueueCircularArray<>(3);
        assertThat(q.offer(10)).isTrue();
        assertThat(q.size()).isEqualTo(1);
        assertThat(q.peek()).isEqualTo(10);
    }

    @Test
    void enqueueOnFullThrows() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(2);
        q.enqueue(1);
        q.enqueue(2);
        assertThatThrownBy(() -> q.enqueue(3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Queue is full");
    }

    @Test
    void dequeueOnEmptyThrows() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(2);
        assertThatThrownBy(q::dequeue)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void pollReturnsNullOnEmpty() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(2);
        assertThat(q.poll()).isNull();
    }

    @Test
    void pollReturnsFrontAndRemovesIt() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(3);
        q.enqueue(5);
        q.enqueue(6);

        assertThat(q.poll()).isEqualTo(5);
        assertThat(q.size()).isEqualTo(1);
    }

    @Test
    void peekReturnsFrontWithoutRemoving() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(3);
        q.enqueue(42);

        assertThat(q.peek()).isEqualTo(42);
        assertThat(q.size()).isEqualTo(1);
    }

    @Test
    void peekOnEmptyThrows() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(3);
        assertThatThrownBy(q::peek)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Queue is empty");
    }

    @Test
    void containsAndSearchWork() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
        q.enqueue(7);
        q.enqueue(8);

        assertThat(q.contains(7)).isTrue();
        assertThat(q.contains(100)).isFalse();

        assertThat(q.search(8)).isEqualTo(1);
        assertThat(q.search(100)).isEqualTo(-1);
    }

    @Test
    void clearEmptiesTheQueue() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
        q.enqueue(1);
        q.enqueue(2);

        q.clear();
        assertThat(q.isEmpty()).isTrue();
        assertThat(q.size()).isEqualTo(0);
        assertThat(q.toString()).contains("Front ->  -> Rear");
    }

    @Test
    void reverseReversesTheQueue() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
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
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        QueueCircularArray<Integer> reversed = (QueueCircularArray<Integer>) q.reverseCopy();

        assertThat(reversed.dequeue()).isEqualTo(3);
        assertThat(reversed.dequeue()).isEqualTo(2);
        assertThat(reversed.dequeue()).isEqualTo(1);

        // original unchanged
        assertThat(q.size()).isEqualTo(3);
    }

    @Test
    void copyReturnsShallowClone() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
        q.enqueue(10);
        q.enqueue(20);

        QueueCircularArray<Integer> copy = (QueueCircularArray<Integer>) q.copy();

        assertThat(copy.size()).isEqualTo(2);
        assertThat(copy.dequeue()).isEqualTo(10);
        assertThat(copy.dequeue()).isEqualTo(20);

        // original unchanged
        assertThat(q.size()).isEqualTo(2);
    }

    @Test
    void toStringWorks() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(5);
        q.enqueue(1);
        q.enqueue(2);
        assertThat(q.toString()).contains("Front -> 1 -> 2 -> Rear");
    }

    @Test
    void handlesWrapAroundCorrectly() {
        QueueCircularArray<Integer> q = new QueueCircularArray<>(3);
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue(); // remove 1
        q.enqueue(3);
        q.enqueue(4); // wrap-around occurs here

        assertThat(q.contains(4)).isTrue();
        assertThat(q.toString()).contains("Front -> 2 -> 3 -> 4 -> Rear");
    }
}
