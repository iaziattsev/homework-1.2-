package LinkedList;

public interface LinkedListNode<T> {
    T getValue();

    void setValue(T value);

    LinkedListNodeImpl<T> getNext();

    void setNext(LinkedListNodeImpl<T> node);
}
