package com.algolib.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class DoublyLinkedList<T extends Comparable<T>> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoublyLinkedList(T data) {
        ListNode<T> listNode = new ListNode<>(data);
        this.head = listNode;
        this.tail = listNode;
        this.size = 1;
    }

    public DoublyLinkedList(T[] dataArray) {
        for (T data : dataArray) {
            add(data);
        }
    }

    private ListNode<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        ListNode<T> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    public Integer getNodeIndexByData(T data) {
        ListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.getData(), data)) {
                return i;
            }
            node = node.next;
        }
        return null; // not found
    }

    public void add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addToHead(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Trying to add at an invalid index: " + index);
        }

        ListNode<T> newNode = new ListNode<>(data);

        // Insert at head
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (size == 0) {
                tail = newNode; // If list was empty
            }
        }
        // Insert at tail
        else if (index == size) {
            if (tail != null) {
                tail.next = newNode;
            }
            newNode.prev = tail;
            tail = newNode;
        }
        // Insert in the middle
        else {
            ListNode<T> oldNode = getNodeAtIndex(index);
            ListNode<T> prev = oldNode.prev;

            prev.next = newNode;
            newNode.prev = prev;
            newNode.next = oldNode;
            oldNode.prev = newNode;
        }

        size++;
    }

    public T deleteHead() {
        if (isEmpty()) {
            System.out.println("List is already empty.");
            return null;
        }

        ListNode<T> oldHead = head;
        head = head.next;
        head.prev = null;
        oldHead.next = null;
        size--;

        if (isEmpty()) {
            // List became empty after deletion
            tail = null;
        }

        return oldHead.getData();
    }

    public T deleteTail() {
        if (isEmpty()) {
            System.out.println("List is already empty.");
            return null;
        }

        ListNode<T> oldTail = tail;

        if (head == tail) {
            // Only one element in the list
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        oldTail.prev = null;

        size--;
        return oldTail.getData();
    }

    public T deleteAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Trying to delete an invalid index: " + index);
        }

        if (index == 0) {
            return deleteHead();
        } else if (index == size - 1) {
            return deleteTail();
        } else {
            ListNode<T> nodeToDelete = getNodeAtIndex(index);
            ListNode<T> prev = nodeToDelete.prev;
            ListNode<T> next = nodeToDelete.next;

            prev.next = next;
            next.prev = prev;

            // Clear references for GC
            nodeToDelete.next = null;
            nodeToDelete.prev = null;

            size--;
            return nodeToDelete.getData();
        }
    }

    public void reverseLinkedList() {
        ListNode<T> current = head;
        ListNode<T> oldPrev = null;

        // Swap next and prev for all nodes
        while (current != null) {
            oldPrev = current.prev;
            current.prev = current.next;
            current.next = oldPrev;
            current = current.prev; // Move to "next" node, which is original prev
        }

        // Swap head and tail
        if (oldPrev != null) {
            // oldPrev.prev is the new head after reversal
            ListNode<T> oldHead = head;
            head = oldPrev.prev;
            tail = oldHead;
        }
    }

    public void reverseInGroups(int k) {
        if (head == null || k <= 1) return;
        head = reverseGroupHelper(head, k);

        // Recalculate tail
        ListNode<T> oldHead = head;
        while (oldHead.next != null) oldHead = oldHead.next;
        tail = oldHead;
    }

    // Returns new head of the group
    private ListNode<T> reverseGroupHelper(ListNode<T> node, int k) {
        ListNode<T> current = node;
        int count = 0;

        // Check if there are at least k nodes
        while (current != null && count < k) {
            current = current.next;
            count++;
        }

        if (count < k) return node;

        // Reverse k nodes
        ListNode<T> prev = null;
        current = node;

        for (int i = 0; i < k; i++) {
            ListNode<T> oldNext = current.next;

            current.next = prev;
            current.prev = oldNext;

            prev = current;
            current = oldNext;
        }

        // Recurse on remaining list
        ListNode<T> newNextHead = reverseGroupHelper(current, k);

        // Connect reversed group to the next group
        node.next = newNextHead;
        if (newNextHead != null) newNextHead.prev = node;

        // Set prev of new head to null
        if (prev != null) {
            prev.prev = null;
        }

        return prev;
    }

    private ListNode<T> findTheMiddleNodeData(ListNode<T> node) {
        if (node == null) return null;

        ListNode<T> slow = node;
        ListNode<T> fast = node.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public void sort() {
        head = mergeSort(head);

        tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
    }

    private ListNode<T> mergeSort(ListNode<T> node) {
        // Base case
        if (node == null || node.next == null) return node;

        ListNode<T> middle = findTheMiddleNodeData(node);
        ListNode<T> nextOfMiddle = middle.next;
        middle.next = null;

        ListNode<T> left = mergeSort(node);
        ListNode<T> right = mergeSort(nextOfMiddle);

        return mergeTwoSortedLists(left, right);
    }

    private ListNode<T> mergeTwoSortedLists(ListNode<T> left, ListNode<T> right) {
        // Dummy node to simplify appending and edge cases
        ListNode<T> dummy = new ListNode<>(null);
        ListNode<T> tail = dummy;

        // Merging logic
        while (left != null && right != null) {
            if (left.data.compareTo(right.data) <= 0) {
                tail.next = left;
                left.prev = tail;
                left = left.next;
            } else {
                tail.next = right;
                right.prev = tail;
                right = right.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes
        if (left != null) {
            tail.next = left;
            left.prev = tail;
        } else {
            tail.next = right;
            right.prev = tail;
        }

        dummy.next.prev = null;
        return dummy.next;
    }

    public void removeDuplicates() {
        if (head == null) return;

        Set<T> seen = new HashSet<>();
        ListNode<T> current = head;
        seen.add(current.data);

        while (current.next != null) {
            if (seen.contains(current.next.data)) {
                // Duplicate found → remove it
                ListNode<T> duplicate = current.next;
                current.next = duplicate.next;

                if (duplicate.next != null) {
                    duplicate.next.prev = current;
                }

                size--;
            } else {
                seen.add(current.next.data);
                current = current.next;
            }
        }

        // Update tail
        tail = current;
    }

    public void generateCyclicList(int n, int cycleIndex, Function<Integer, T> generator) {
        if (n <= 0 || cycleIndex < 0 || cycleIndex >= n) {
            throw new IllegalArgumentException("Invalid size or cycle index");
        }

        head = null;
        tail = null;
        size = 0;

        ListNode<T> cycleNode = null;
        ListNode<T> prev = null;

        for (int i = 0; i < n; i++) {
            T value = generator.apply(i);
            ListNode<T> newNode = new ListNode<>(value);

            if (i == 0) {
                head = newNode;
            } else {
                prev.next = newNode;
                newNode.prev = prev;
            }

            if (i == cycleIndex) {
                cycleNode = newNode;
            }

            prev = newNode;
            size++;
        }

        tail = prev;
        tail.next = cycleNode;
        cycleNode.prev = tail;
    }

    public boolean hasCycle() {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode<T> slow = head;
        ListNode<T> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps

            if (slow == fast) {
                return true;            // Cycle detected
            }
        }

        return false;                   // No cycle
    }

    public T detectStartOfCycle() {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode<T> slow = head;
        ListNode<T> fast = head;
        ListNode<T> start = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps

            if (slow == fast) {
                while (start != slow) {
                    start = start.next;
                    slow = slow.next;
                }
                return start.data;
            }
        }

        return null;
    }

    public void swap(int index1, int index2) {
        if (index1 == index2) return;

        // Validate indices
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            throw new IndexOutOfBoundsException("Invalid indices: " + index1 + ", " + index2);
        }

        // Get nodes at both indices
        ListNode<T> node1 = getNodeAtIndex(index1);
        ListNode<T> node2 = getNodeAtIndex(index2);

        // Swap the data
        T tempData = node1.data;
        node1.data = node2.data;
        node2.data = tempData;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() {
        ListNode<T> current = head;
        Set<ListNode<T>> visited = new HashSet<>();

        while (current != null) {
            // Detect cycle
            if (visited.contains(current)) {
                System.out.print(current.getData() + " -> (cycle detected here)");
                break;
            }

            System.out.print(current.getData() + " -> ");
            visited.add(current);
            current = current.next;
        }

        if (current == null) {
            System.out.println("null");
        } else {
            System.out.println(); // cycle case already handled above
        }

        System.out.println("List size (logical count): " + getSize());
    }

    public void printListBackward() {
        ListNode<T> current = tail;
        Set<ListNode<T>> visited = new HashSet<>();

        while (current != null) {
            // Detect cycle
            if (visited.contains(current)) {
                System.out.print(current.getData() + " <- (cycle detected here)");
                break;
            }

            System.out.print(current.getData() + " <- ");
            visited.add(current);
            current = current.prev;
        }

        if (current == null) {
            System.out.println("null");
        } else {
            System.out.println(); // in case of cycle
        }

        System.out.println("List size (logical count): " + getSize());
    }

    public boolean isEqual(DoublyLinkedList<T> other) {
        if (this.size != other.size) {
            return false;
        }

        ListNode<T> current1 = this.head;
        ListNode<T> current2 = other.head;

        while (current1 != null && current2 != null) {
            if (current1.getData() != current2.getData()) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DoublyLinkedList<?> other = (DoublyLinkedList<?>) obj;

        ListNode<T> current1 = this.head;
        ListNode<?> current2 = other.head;

        while (current1 != null && current2 != null) {
            if (!Objects.equals(current1.data, current2.data)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return current1 == null && current2 == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        ListNode<T> current = head;

        while (current != null) {
            result = 31 * result + (current.data != null ? current.data.hashCode() : 0);
            current = current.next;
        }

        return result;
    }

    private static class ListNode<T> {
        private T data;
        private ListNode<T> prev;
        private ListNode<T> next;

        public ListNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public ListNode<T> getNext() {
            return next;
        }

        public void setNext(ListNode<T> next) {
            this.next = next;
        }

        public ListNode<T> getPrev() {
            return prev;
        }

        public void setPrev(ListNode<T> prev) {
            this.prev = prev;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true; // same reference
            if (obj == null || getClass() != obj.getClass()) return false;

            ListNode<?> other = (ListNode<?>) obj;

            // Compare the data
            return Objects.equals(this.data, other.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
}
