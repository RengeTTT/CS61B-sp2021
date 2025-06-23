package deque;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.printDeque();
    }
    @Test
    public void addLastTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.printDeque();
    }
    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeFirst();
        deque.printDeque();
    }
    @Test
    public void removeLastTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.printDeque();
    }
    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.removeLast();
        deque.removeFirst();
        deque.printDeque();
    }
    @Test
    public void removeLastOnlyTest () {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeLast();
        deque.printDeque();
    }
    @Test
    public void removeFirstOnlyTest () {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.addFirst(1);
        deque.addFirst(2);

        deque.printDeque();
    }
    @Test
    public void isEmptyTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.get(0);
        assert deque.isEmpty();
    }
    @Test
    public void deepCopyTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addFirst(5);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.printDeque();
        deque.printDeque();
        assertFalse(deque.toString().equals(deque2.toString()));

    }
    @Test
    public void expandCapacityTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.printDeque();
    }
    @Test
    public void shrinkCapacityTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 32; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 30; i++) {
            deque.removeLast();
        }
        deque.addFirst(0);
        deque.printDeque();
    }
    @Test
    public void IteratorTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.removeLast();
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            System.out.println(element);
        }
    }
    @Test
    public void equalsTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(3);
        deque2.addLast(4);
        deque.equals(deque2);
    }
    @Test
    public void comparatorTest() {

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        MaxArrayDeque<Integer> maxAD = new MaxArrayDeque<>(comparator);
        maxAD.addLast(1);
        maxAD.addLast(2);
        maxAD.addLast(3);
        maxAD.addLast(4);
        maxAD.addLast(5);
        maxAD.removeFirst();
        maxAD.removeLast();
        Integer item = maxAD.max(comparator);
        assertEquals(maxAD.get(2) + "," + item,maxAD.get(2), item);
    }

}
