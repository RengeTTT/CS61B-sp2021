package deque;

import java.util.Iterator;
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;

    private int head, tail; // 循环数组head，tail跟踪队列头尾
    private double usageRate;

    private int size;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }
//    public ArrayDeque(ArrayDeque<T> other) {
//
//        int capacity = other.items.length;
//        T[] newItems = (T[]) new Object[capacity];
//        for (int i  = 0; i < other.size(); i++) {
//            int index = (other.head + i) & (capacity - 1);
//            newItems[index] = other.items[index];
//        }
//
//        this.size = other.size();
//        this.head = other.head;
//        this.tail = other.tail;
//        this.usageRate = other.usageRate;
//        this.items = newItems;
//    }

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
        for (int i = 0; i < size(); i++) {
            newItems[i] = this.get(i);
        }
        this.head = 0;
        this.tail = size;
        this.items = newItems;

    }
    private void shrinkCapacity() {
        if (items.length <= DEFAULT_CAPACITY) {
            return;
        }
        int newCapacity = items.length;
        double newUsageRate = usageRate;
        while (true) {
            newCapacity = newCapacity >>> 1;
            newUsageRate = 1.0 * size() / newCapacity;
            if (newUsageRate > DEFAULT_LOAD_FACTOR) {
                break;
            }
        }
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            newItems[i] = this.get(i);
        }
        this.head = 0;
        this.tail = size;
        this.usageRate = newUsageRate;
        this.items = newItems;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int start = head & (items.length - 1);
        int count = 0;
        @Override
        public boolean hasNext() {
            return count < size;
        }
        @Override
        public T next() {
            T item = items[start];
            start = (start + 1) & (items.length - 1);
            count++;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Deque)) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) obj;
        if (other.size() != this.size()) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisItem = thisIterator.next();
            Object otherItem = otherIterator.next();
            if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }
    public void printDeque() {

        for (int i  = 0;  i < size(); i++) {
            int index = (i + head) & (items.length - 1);
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

}
