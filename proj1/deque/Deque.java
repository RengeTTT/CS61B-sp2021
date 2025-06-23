package deque;

public interface Deque<T> extends Iterable<T> {
    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);

    boolean equals(Object other);

    void printDeque();
}
