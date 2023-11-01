package lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SinglyLinkedList<T> implements BasicList<T> {

    private SinglyLinkedListNode<T> head = null;
    private SinglyLinkedListNode<T> tail = null;

    public SinglyLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SinglyLinkedListNode<T> next) {
        head = next;
    }
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        SinglyLinkedListNode<T> current = head;
        int count = 0;
        while(current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean contains(T value) {
        SinglyLinkedListNode<T> current = head;
        while(current != null) {
            if(current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        SinglyLinkedListNode<T> current = head;
        int count = 0;

        while(current != null) {
            if(count == index) {
                return (T) current.data;
            }
            count++;
            current = current.next;
        }
        return null;
    }

    public void append(T value) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(value); // create a new node
        if(isEmpty()) { // if list is empty
            head = newNode; // set the head to the new node
        } else { // if list is not empty
            tail.next = newNode; // set the tail.next to the new node
        }
        tail = newNode; // set the tail to the new node
    }

    public boolean remove(T value) { // remove the first occurrence of value
        SinglyLinkedListNode<T> current = head;
        SinglyLinkedListNode<T> previous = null;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SinglyLinkedListNode<T> current = head;

        while(current != null) {
            sb.append(current.data);
            sb.append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        SinglyLinkedListNode<T> current = head;
        int index = 0;
        while(current != null) {
            array[index] = current.data;
            index++;
            current = current.next;
        }
        return array;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        SinglyLinkedListNode<T> current = head;
        while(current != null) {
            arrayList.add(current.data);
            current = current.next;
        }
        return arrayList;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        SinglyLinkedListNode<T> current = head;
        while(current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
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
        if (sorted == null || comparator.compare(newNode.data, sorted.data) <= 0) { // If the new node is less than or equal to the first element, insert it before the current head.
            newNode.next = sorted;
            return newNode;
        } else { // Otherwise, find the position to insert the new node
            SinglyLinkedListNode<T> current = sorted;
            while (current.next != null && comparator.compare(newNode.data, (T) current.next.data) > 0) { // Loop until current.next is null or the new node is less than the next node
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            return sorted;
        }
    }

}
