package LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testAdd() {
        LinkedListImpl<Integer> testLinkedList = new LinkedListImpl<>();

        testLinkedList.add(1);
        testLinkedList.add(2);
        testLinkedList.add(3);
        testLinkedList.add(4);
        testLinkedList.add(5);

        Assert.assertEquals((Integer) 5, testLinkedList.get(4));
    }

    @Test
    public void testInsert() {
        LinkedListImpl<Integer> testLinkedList = new LinkedListImpl<>();

        testLinkedList.add(1);
        testLinkedList.add(2);
        testLinkedList.add(4);
        testLinkedList.add(5);

        testLinkedList.insert(2, 3);

        Assert.assertEquals((Integer) 3, testLinkedList.get(2));
    }

    @Test
    public void testRemove() {
        LinkedListImpl<Integer> testLinkedList = new LinkedListImpl<>();

        testLinkedList.add(1);
        testLinkedList.add(2);
        testLinkedList.add(3);
        testLinkedList.add(4);
        testLinkedList.add(5);

        testLinkedList.remove(0);

        Assert.assertEquals((Integer) 2, testLinkedList.get(0));
    }

    @Test
    public void testGetSize() {
        LinkedListImpl<Integer> testLinkedList = new LinkedListImpl<>();

        testLinkedList.add(1);
        testLinkedList.add(2);

        Assert.assertEquals(2, testLinkedList.size());

        testLinkedList.add(3);
        testLinkedList.add(4);
        testLinkedList.add(5);

        Assert.assertEquals(5, testLinkedList.size());
    }
}
