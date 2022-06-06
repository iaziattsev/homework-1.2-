package LinkedList;

public class LinkedListNodeImpl<T> implements LinkedListNode<T> {

    private T value;
    private LinkedListNodeImpl<T> next;

    public LinkedListNodeImpl(T value) {
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public LinkedListNodeImpl<T> getNext() {
        return next;
    }

    @Override
    public void setNext(LinkedListNodeImpl<T> next) {
        this.next = next;
    } {
}
