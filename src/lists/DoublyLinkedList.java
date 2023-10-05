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
        T comparator = null;
    }

    @Override
    public void add(T value) {
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
            return false; // return false if the list is empty
        } else {
            if (head.data.equals(value)) { // if the head is equal to the value
                if (tail == head) { // if the tail is equal to the head
                    head = null; // set the head to null
                    tail = null; // set the tail to null
                } else { // if the tail is not equal to the head
                    head = head.next; // set the head to the next node
                    head.prev = null; // set the head.prev to null
                }
                return true; // return true
            } else {
                DoublyLinkedListNode<T> current = head; // initialize current
                while (current != null && !current.data.equals(value)) { // loop until current.next is null or current.data is equal to value
                    current = current.next; // set current to the next node
                }

                if (current == null) { // if current is null
                    return false; // return false
                } else { // if current is not null
                    if (current == tail) { // if current is equal to tail
                        tail = tail.prev; // set tail to tail.prev
                        tail.next = null; // set tail.next to null
                    } else {
                        current.prev.next = current.next; // set current.prev.next to current.next
                        current.next.prev = current.prev; // set current.next.prev to current.prev
                    }
                    return true; // return true
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
        if (isEmpty()) {
            throw new IllegalArgumentException("the list is empty"); // throw an exception if the list is empty
        }
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
        System.out.println("Nodes of doubly linked list: ");
        while(current != null) {
            result.append(current.data).append(", ");
            current = current.next;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Station> list = new DoublyLinkedList<>();
        Station station = new Station("1", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        Station station2 = new Station("2", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b");
        Station station3 = new Station("3", "c", "c", "c", "c", "c", "c", "c", "c", "c", "c");

        list.add(station3);
        //list.addSorted(station2, Comparator.comparing(Station::getNameShort));
        //list.addSorted(station, Comparator.comparing(Station::getNameShort));
        list.add(station);
        list.add(station2);

        Comparator<Station> comparator = Comparator.comparing(Station::getNameShort);
        list = Sort.insertionSortDoublyLinkedList(list, comparator);

        //list.remove(station2);
        System.out.println(list);
    }
}
