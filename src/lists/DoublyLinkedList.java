package lists;

import model.Station;
import utils.Sort;

import java.util.Comparator;

public class DoublyLinkedList<T extends Comparable<T>>  implements BasicList<T>{

    public DoublyLinkedListNode<T> head;
    public DoublyLinkedListNode<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void append(T value) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(value);
        if(isEmpty()) { // if list is empty
            head = tail = newNode; // set the head to the new node
            head.prev = null; // set the head.prev to null
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = null; // set the tail.next to null
    }

    public void addSorted(T value, Comparator<T> comparator) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(value);
        if (isEmpty()) {
            // If the list is empty, set the new node as the head and tail.
            head = tail = newNode;
            head.prev = null;
            tail.next = null;
        } else if (comparator.compare(value, head.data) <= 0) {
            // If the value is less than or equal to the first element,
            // insert it before the current head.
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            head.prev = null;
        } else {
            DoublyLinkedListNode<T> current = head;
            while (current != null && comparator.compare(value, current.data) > 0) {
                current = current.next;
            }

            if (current == null) {
                // If we reached the end of the list, insert the new node at the end.
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                tail.next = null;
            } else {
                // Insert the new node before the current node.
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
    }

    public boolean remove(T value) {
        if (isEmpty()) {
            return false; // value is NOT 'removed'
        } else {
            if (head.data.equals(value)) { // if the first node is equal to the value
                if (tail == head) { // and there is only one node in the list
                    head = null;
                    tail = null;
                } else { // and there are more than one node in the list
                    head = head.next;
                    head.prev = null;
                }
                return true; // value is 'removed'
            } else {
                DoublyLinkedListNode<T> current = head;
                while (current != null && !current.data.equals(value)) { // loop until current.next is null or current.data is equal to value
                    current = current.next;
                }
                if (current == null) { // if the value is NOT in the list
                    return false; // value is NOT 'removed'
                } else { // if the value to be removed is found
                    if (current == tail) { // if the value is the last node
                        tail = tail.prev;
                        tail.next = null;
                    } else { // if the value is NOT the last node
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return true; // value is 'removed'
                }
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null; // return true if head is null
    }

    @Override
    public int size() {
        int count = 0; // initialize count
        DoublyLinkedListNode current = head; // initialize current
        while(current != null) { // loop until current.next is null
            count++; // increment count
            current = current.next; // set current to the next node
        }
        return count; // return count
    }

    @Override
    public boolean contains(T value) {
        assert value != null : "value can't be null";

        DoublyLinkedListNode current = head; // initialize current
        while(current != null) { // loop until current.next is null
            if(current.data.equals(value)) {
                return true; // return true if value is found
            }
            current = current.next; // set current to the next node
        }
        return false; // return false if value is not found
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(head == null) {
            return "DoublyLinkedList is empty";
        }
        DoublyLinkedListNode current = head;
        result.append("Nodes of doubly linked list: ");
        while(current != null) {
            result.append(current.data).append(", ");
            current = current.next;
        }
        return result.toString();
    }

}
