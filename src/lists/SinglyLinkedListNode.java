package lists;

public class SinglyLinkedListNode<T> {
    public T data;
    public SinglyLinkedListNode<T> next;

    public SinglyLinkedListNode(T data) {
        this.data = data;
        this.next = null;
    }
}
