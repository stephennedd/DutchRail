package utils;

import lists.DoublyLinkedList;
import lists.DoublyLinkedListNode;
import model.Connection;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort {

    // Insertion sort doubly linked list implementation
    public static <T extends Comparable<T>> DoublyLinkedList<T> insertionSortDoublyLinkedList(DoublyLinkedList<T> list, Comparator<T> comparator) {
        if (list == null) {
            // Nothing to sort for a null list.
            throw new IllegalArgumentException("List cannot be null.");
        }

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

    // Insertion sort arraylist implementation
    public static <T extends Comparable<T>> ArrayList<T> insertionSortArrayList(ArrayList<T> list, Comparator<T> comparator) {
        if (list == null) {
            // Nothing to sort for a null list.
            throw new IllegalArgumentException("List cannot be null.");
        }

        if (list.isEmpty() || list.size() == 1) {
            // Nothing to sort for an empty list or a list with only one element.
            return list;
        }

        // Iterate through the original list
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);

            // Find the correct position for the key in the sorted list
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            // Insert the key at its correct position in the sorted list
            list.set(j + 1, key);
        }

        return list;
    }

    // Mergesort on an arraylist implementation
    public static <T extends Comparable<T>> ArrayList<T> mergeSortArrayList(ArrayList<T> list, Comparator<T> comparator) {
        if (list == null) {
            // Nothing to sort for a null list.
            throw new IllegalArgumentException("List cannot be null.");
        }

        if (list.isEmpty() || list.size() == 1) {
            // Nothing to sort for an empty list or a list with only one element.
            return list;
        }

        // Split the list in two halves
        int middle = list.size() / 2;
        ArrayList<T> left = new ArrayList<>(list.subList(0, middle));
        ArrayList<T> right = new ArrayList<>(list.subList(middle, list.size()));

        // Sort the two halves
        left = mergeSortArrayList(left, comparator);
        right = mergeSortArrayList(right, comparator);

        // Merge the two sorted halves
        return mergeArrayList(left, right, comparator);
    }

    private static <T extends Comparable<T>> ArrayList<T> mergeArrayList(ArrayList<T> left, ArrayList<T> right, Comparator<T> comparator) {
        ArrayList<T> merged = new ArrayList<>();

        // Merge the two sorted halves
        while (!left.isEmpty() && !right.isEmpty()) {
            if (comparator.compare(left.get(0), right.get(0)) <= 0) {
                merged.add(left.remove(0));
            } else {
                merged.add(right.remove(0));
            }
        }

        // Add the remaining elements
        merged.addAll(left);
        merged.addAll(right);

        return merged;
    }


}