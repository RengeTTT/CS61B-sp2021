package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    private static class MaxArrayDequeComparator<T> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return (Integer) o1 - (Integer) o2;
        }
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            T current = this.get(i);
            if (c.compare(max, current) < 0) {
                max = current;
            }
        }
        return max;
    }

}
