package deque;

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    boolean isEmpty();

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);

    void printDeque();
}
