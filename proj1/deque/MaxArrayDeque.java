package deque;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {



    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    private static class MaxArrayDequeComparator<T> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            int cmp = (Integer) o1 - (Integer) o2;
            return cmp;
        }
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = items[head & (items.length - 1)];
        for (int i = 0; i < this.size(); i++) {
            T current = items[(head + i) & (items.length - 1)];
            if (comparator.compare(max, current) < 0) {
                max = current;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = items[head & (items.length - 1)];
        for (int i = 0; i < this.size(); i++) {
            T current = items[(head + i) & (items.length - 1)];
            if (comparator.compare(max, current) < 0) {
                max = current;
            }
        }
        return max;
    }

    public static <T> Comparator<T> getComparator() {
        return new MaxArrayDequeComparator<>();
    }
}
