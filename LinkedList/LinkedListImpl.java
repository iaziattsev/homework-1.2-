package LinkedList;

public class LinkedListImpl<T> implements LinkedList<T>{

    private LinkedListNodeImpl<T> firstNode;
    private int size;

    @Override
    public T get(int index) {
        return getNodeByIndex(index).getValue();
    }

    @Override
    public void add(T value) {
        if (size == 0)
            firstNode = new LinkedListNodeImpl<>(value);
        else
            getNodeByIndex(size - 1).setNext(new LinkedListNodeImpl<>(value));

        size++;
    }

    @Override
    public void insert(int index, T value) {
        LinkedListNodeImpl<T> linkedListNode = new LinkedListNodeImpl<>(value);

        if (index == 0) {
            linkedListNode.setNext(firstNode);
            firstNode = linkedListNode;
        }
        else if (index == size) {
            add(value);
            return;
        }
        else {
            LinkedListNodeImpl<T> nodeLeft = getNodeByIndex(index - 1);
            LinkedListNodeImpl<T> nodeRight = nodeLeft.getNext();

            nodeLeft.setNext(linkedListNode);
            linkedListNode.setNext(nodeRight);
        }

        size++;
    }

    @Override
    public void remove(int index) {
        LinkedListNodeImpl<T> nodeLeft = getNodeByIndex(index - 1);

        if (index == 0) {
            firstNode = nodeLeft.getNext();
        }
        else if (index == size - 1) {
            nodeLeft.setNext(null);
        }
        else {
            LinkedListNodeImpl<T> nodeToRemove = nodeLeft.getNext();
            LinkedListNodeImpl<T> nodeRight = nodeToRemove.getNext();

            nodeLeft.setNext(nodeRight);
        }

        size--;
    }

    @Override
    public int size() {
        return size;
    }

    private LinkedListNodeImpl<T> getNodeByIndex(int index) {
        LinkedListNodeImpl<T> currentNode = firstNode;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode;
    } {
}
