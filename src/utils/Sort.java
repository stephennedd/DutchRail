package utils;

import lists.DoublyLinkedList;
import lists.DoublyLinkedListNode;

import java.util.Comparator;

public class Sort {

    // Insertion sort implementation
    public static <T extends Comparable<T>> DoublyLinkedList<T> insertionSortDoublyLinkedList(DoublyLinkedList<T> list, Comparator<T> comparator) {
        if (list.isEmpty() || list.size() == 1) {
            // Nothing to sort for an empty list or a list with only one element.
            return list;
        }

        // Create a new empty list for the sorted result
        DoublyLinkedList<T> sortedList = new DoublyLinkedList<>();

        // Iterate through the original list
        for (DoublyLinkedListNode<T> current = list.head; current != null; current = current.next) {
            T key = current.data;

            // Find the correct position for the key in the sorted list
            DoublyLinkedListNode<T> prev = sortedList.tail;
            while (prev != null && comparator.compare(prev.data, key) > 0) {
                prev = prev.prev;
            }

            // Insert the key at its correct position in the sorted list
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(key);
            if (sortedList.head == null || comparator.compare(sortedList.head.data, key) > 0) {
                // Insert at the beginning
                newNode.next = sortedList.head;
                if (sortedList.head != null) {
                    sortedList.head.prev = newNode;
                }
                sortedList.head = newNode;
                if (sortedList.tail == null) {
                    // Update the tail if inserting at the end
                    sortedList.tail = newNode;
                }
            } else {
                newNode.next = prev.next;
                if (prev.next != null) {
                    prev.next.prev = newNode;
                } else {
                    // Update the tail if inserting at the end
                    sortedList.tail = newNode;
                }
                prev.next = newNode;
                newNode.prev = prev;
            }
        }

        return sortedList;
    }

    // Merge sort implementation
    public static <T extends Comparable<T>> DoublyLinkedList<T> mergeSortDoublyLinkedList(DoublyLinkedList<T> list) {
        DoublyLinkedList<T> sortedList = new DoublyLinkedList<>();

        return sortedList;
    }
}