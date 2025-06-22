package deque;

import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    protected T[] items;

    protected int head, tail; // 循环数组head，tail跟踪队列头尾
    protected double usageRate;

    protected int size;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }
    public ArrayDeque(ArrayDeque<T> other) {

        int capacity = other.items.length;
        T[] newItems = (T[]) new Object[capacity];
        for (int i  = 0; i < other.size(); i++) {
            int index = (other.head + i) & (capacity - 1);
            newItems[index] = other.items[index];
        }

        this.size = other.size();
        this.head = other.head;
        this.tail = other.tail;
        this.usageRate = other.usageRate;
        this.items = newItems;
    }

    public void addFirst(T item) {

        if (size == items.length) {
            expandCapacity();
        }
        head = (head - 1) & (items.length - 1); // 位运算加速效率
        items[head] = item;

        size++;
        usageRate = size * 1.0 / items.length;
    }
    public void addLast(T item) {

        if (size == items.length) {
            expandCapacity();
        }
        items[tail] = item;
        tail = (tail + 1) & (items.length - 1);

        size++;
        usageRate = size * 1.0 / items.length;
    }
    public T removeFirst() {

        if (isEmpty()) {
            return null;
        }

        T item = items[head];
        items[head] = null;
        head = (head + 1) & (items.length - 1);

        size--;
        usageRate = size * 1.0 / items.length;

        if (usageRate < DEFAULT_LOAD_FACTOR) {
            shrinkCapacity();
        }
        return item;
    }
    public T removeLast() {

        if (isEmpty()) {
            return null;
        }

        tail = (tail - 1) & (items.length - 1);
        T item = items[tail];
        items[tail] = null;
        size--;
        usageRate = size * 1.0 / items.length;

        if (usageRate < DEFAULT_LOAD_FACTOR) {
            shrinkCapacity();
        }
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public T get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) & (items.length - 1)];
    }
    public int size() {
        return size;
    }

    private void expandCapacity() {
        int oldCapacity = items.length;
        int newCapacity = Math.min(MAX_CAPACITY, oldCapacity << 1);
        T[] newItems = (T[]) new Object[newCapacity];
        int newHead = newCapacity - (items.length - head);
        int newTail = tail;
        for (int i = 0; i < size(); i++) {
            int newIndex = (newHead + i) & (newCapacity - 1);
            int index = (head + i) & (oldCapacity - 1);
            newItems[newIndex] = items[index];
        }
        this.head = newHead;
        this.tail = newTail;
        this.items = newItems;

    }
    private void shrinkCapacity() {
        if (items.length <= DEFAULT_CAPACITY) {
            return;
        }
        int newCapacity = items.length;
        int oldCapacity = items.length;
        double newUsageRate =  usageRate;
        while (true) {
            newCapacity = newCapacity >>> 1;
            newUsageRate = 1.0 * size() / newCapacity;
            if (newUsageRate > DEFAULT_LOAD_FACTOR) {
                break;
            }
        }
        T[] newItems = (T[]) new Object[newCapacity];
        int newHead = newCapacity - (items.length - head);
        int newTail = tail;
        for (int i = 0; i < size(); i++) {
            int newIndex = (newHead + i) & (newCapacity - 1);
            int index = (head + i) & (oldCapacity - 1);
            newItems[newIndex] = items[index];
        }
        this.head = newHead;
        this.tail = newTail;
        this.usageRate = newUsageRate;
        this.items = newItems;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int start = head & (items.length - 1);
        int end = tail;
        @Override
        public boolean hasNext() {
            if (start < end) {
                return true;
            }
            return false;
        }
        @Override
        public T next() {
            T item = items[start];
            start = (start + 1) & (items.length - 1);
            return item;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    public boolean equals(Object obj) {

        if (obj instanceof LinkedListDeque || obj instanceof ArrayDeque) {
            ArrayDeque<T> objDeque = (ArrayDeque<T>) obj;
            if (size != objDeque.size()) {
                return false;
            }
            Iterator<T> objIterator = iterator();
            Iterator<T> thisItr = this.iterator();
            while (thisItr.hasNext()) {
                if (objIterator.next() != thisItr.next()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public void printDeque() {

        for (int i  = 0;  i < size(); i++) {
            int index = (i + head) & (items.length - 1);
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

}
