package lists;

public class DoublyLinkedListNode<T> {
    public T data;
    public DoublyLinkedListNode next;
    public DoublyLinkedListNode prev;

    public DoublyLinkedListNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
