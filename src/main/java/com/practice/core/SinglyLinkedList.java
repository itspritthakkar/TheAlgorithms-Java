package com.practice.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class SinglyLinkedList <T extends Comparable<T>> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public SinglyLinkedList(T data) {
        ListNode<T> listNode = new ListNode<>(data);
        this.head = listNode;
        this.tail = listNode;
        this.size = 1;
    }

    public SinglyLinkedList(T[] dataArray) {
        for (T data : dataArray) {
            add(data);
        }
    }

    private ListNode<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        ListNode<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private ListNode<T> getPrevNodeAtIndex(int index) {
        if (index <= 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for previous node: " + index);
        }

        return getNodeAtIndex(index - 1);
    }

    private NodePair<T> getCurrentAndPrevNodeAtIndex(int index) {
        ListNode<T> prev = getPrevNodeAtIndex(index-1);
        ListNode<T> current = prev.next;

        return new NodePair<T>(prev, current);
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
            head = newNode;
            if (size == 0) {
                tail = newNode; // If list was empty
            }
        }
        // Insert at tail
        else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        }
        // Insert in the middle
        else {
            ListNode<T> prev = getPrevNodeAtIndex(index);
            newNode.setNext(prev.getNext());
            prev.setNext(newNode);
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
            ListNode<T> prev = head;
            while (prev.next != tail) {
                prev = prev.next;
            }
            prev.next = null;
            tail = prev;
        }

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
            NodePair<T> nodePair = getCurrentAndPrevNodeAtIndex(index);

            ListNode<T> prev = nodePair.getPrev();
            ListNode<T> nodeToDelete = nodePair.getCurrent();

            prev.next = nodeToDelete.next;
            nodeToDelete.next = null;

            size--;

            return nodeToDelete.getData();
        }
    }

    public void reverseLinkedList() {
        ListNode<T> prev = null;
        ListNode<T> curr = head;

        while (curr != null) {
            ListNode<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
    }

    public void reverseInGroups(int k) {
        if (head == null || k <= 1) return;

        head = reverseGroupHelper(head, k);
    }

    // Helper returns new head after reversing a group
    private ListNode<T> reverseGroupHelper(ListNode<T> node, int k) {
        ListNode<T> curr = node;
        int count = 0;

        // Step 1: Check if there are at least k nodes to reverse
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }

        if (count < k) return node; // Fewer than k nodes, no reversal

        // Step 2: Reverse k nodes
        ListNode<T> prev = null;
        curr = node;
        for (int i = 0; i < k; i++) {
            ListNode<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: Recurse for next group and connect
        node.next = reverseGroupHelper(curr, k);

        // prev is the new head of this group
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
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes
        tail.next = (left != null) ? left : right;

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
                current.next = current.next.next;
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
            T value = generator.apply(i);           // create T instance safely
            ListNode<T> newNode = new ListNode<>(value);

            if (i == 0) {
                head = newNode;
            } else {
                prev.setNext(newNode);
            }

            if (i == cycleIndex) {
                cycleNode = newNode;
            }

            prev = newNode;
            size++;
        }

        tail = prev;
        tail.setNext(cycleNode);
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

    public boolean isEqual(SinglyLinkedList<T> other) {
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

        SinglyLinkedList<?> other = (SinglyLinkedList<?>) obj;

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

    private static class ListNode <T> {
        private final T data;
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

    private static class NodePair <T> {
        ListNode<T> prev;
        ListNode<T> current;

        NodePair(ListNode<T> prev, ListNode<T> current) {
            this.prev = prev;
            this.current = current;
        }

        public ListNode<T> getPrev() {
            return prev;
        }

        public ListNode<T> getCurrent() {
            return current;
        }
    }
}
