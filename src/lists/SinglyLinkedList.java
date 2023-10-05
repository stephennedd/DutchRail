package lists;

import java.util.Comparator;

public class SinglyLinkedList<T> implements BasicList<T> {

    private SinglyLinkedListNode head = null;
    private SinglyLinkedListNode tail = null;

    public SinglyLinkedListNode getHead() {
        return head;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        SinglyLinkedListNode current = head;
        int count = 0;
        while(current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean contains(T value) {
        SinglyLinkedListNode current = head;
        while(current != null) {
            if(current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void add(T value) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(value); // create a new node
        if(isEmpty()) { // if list is empty
            head = newNode; // set the head to the new node
        } else { // if list is not empty
            tail.next = newNode; // set the tail.next to the new node
        }
        tail = newNode; // set the tail to the new node
    }

    public boolean remove(T value) { // remove the first occurrence of value
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode previous = null;
        while(current != null) { // loop until current.next is null
            if(current.data.equals(value)) { // if value is found
                if(previous == null) { // if value is the head
                    head = current.next; // set the head to the next node
                } else { // if value is not the head
                    previous.next = current.next; // set the previous node to the next node
                }
                return true; // return true if value is found and removed
            } // if value is not found
            previous = current;
            current = current.next;
        }
        return false; // return false if value is not found
    }

    public void print() {
        SinglyLinkedListNode current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        System.out.println("Nodes of the singly linked list: ");
        while(current != null) {
            System.out.print( current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[size()];
        SinglyLinkedListNode current = head;
        int index = 0;

        while(current != null) {
            array[index] = (T) current.data;
            index++;
            current = current.next;
        }

        return array;
    }

    public void sort(Comparator<T> comparator) {
        if (head == null || head.next == null) {
            return; // No need to sort an empty list or a list with one element
        }

        SinglyLinkedListNode<T> sorted = null; // Initialize a new list for sorted elements

        SinglyLinkedListNode<T> current = head;
        while (current != null) {
            SinglyLinkedListNode<T> next = current.next;
            sorted = insertSorted(sorted, current, comparator);
            current = next;
        }

        head = sorted; // Update the head to point to the sorted list
    }

    private SinglyLinkedListNode<T> insertSorted(SinglyLinkedListNode<T> sorted, SinglyLinkedListNode<T> newNode, Comparator<T> comparator) {
        if (sorted == null || comparator.compare(newNode.data, sorted.data) <= 0) {
            newNode.next = sorted;
            return newNode;
        } else {
            SinglyLinkedListNode current = sorted;
            while (current.next != null && comparator.compare(newNode.data, (T) current.next.data) > 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return sorted;
        }
    }


}
