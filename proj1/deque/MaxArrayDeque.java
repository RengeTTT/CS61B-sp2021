package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final ArrayDequeComparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = (ArrayDequeComparator<T>) c;
    }

    private static class ArrayDequeComparator<T> implements Comparator<T> {

       @Override
        public int compare(T o1, T o2) {
            return (Integer) o1 - (Integer) o2;
        }

    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T items[] = this.getItems();
        T max = items[0];
        int head = this.getHead();
        for (int i = 0; i < this.size() - 1; i++) {
            T prev = items[(head + i) & (items.length - 1)];
            T next = items[(head + i + 1) & (items.length - 1)];
            if (comparator.compare(prev, next) > 0) {
                max = prev;
            } else {
                max = next;
            }
        }
        return max;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T items[] = this.getItems();
        T max = items[0];
        int head = this.getHead();
        for (int i = 0; i < this.size() - 1; i++) {
            T prev = items[(head + i) & (items.length - 1)];
            T next = items[(head + i + 1) & (items.length - 1)];
            if (c.compare(prev, next) > 0) {
                max = prev;
            } else {
                max = next;
            }
        }
        return max;
    }
    public static <T> Comparator<T> getComparator() {
        return new ArrayDequeComparator<>();
    }
}
