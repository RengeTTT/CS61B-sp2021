package deque;

public class ArrayDeque<T> {

    T[] items;

    int head, tail; // 循环数组head，tail跟踪队列头尾
    double usageRate;

    int size;
    static final int MAX_CAPACITY = Integer.MAX_VALUE;
    static final int DEFAULT_CAPACITY = 8;
    static final double DEFAULT_LOAD_FACTOR = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }
    public ArrayDeque(ArrayDeque<T> deque) {

        int capacity = deque.items.length;
        T[] newItems = (T[]) new Object[capacity];
        for (int i  = 0; i < deque.size(); i++) {
            int index = (deque.head + i) & (capacity - 1);
            newItems[index] = deque.items[index];
        }

        this.size = deque.size();
        this.head = deque.head;
        this.tail = deque.tail;
        this.usageRate = deque.usageRate;
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

    public void printDeque() {

        for (int i  = 0;  i < size(); i++) {
            int index = (i + head) & (items.length - 1);
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

}
