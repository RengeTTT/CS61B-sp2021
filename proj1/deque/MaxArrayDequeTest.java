package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest {
    private class stringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    private class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
    private class ramdomComparator<T> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return 0;
        }
    }
    @Test
    public void stringComparatorTest() {
        MaxArrayDeque<String> md = new MaxArrayDeque<>(new stringComparator());
        md.addLast("one");
        md.addLast("two");
        md.addLast("z");
        assertEquals("z",md.max());
    }
    @Test
    public void integerComparatorTest() {
        MaxArrayDeque<Integer> md = new MaxArrayDeque<>(new IntegerComparator());
        md.addLast(1);
        md.addLast(2);
        md.addLast(3);
        md.addLast(4);
        assertEquals(4,md.max().intValue());
    }
    @Test
    public void randomComparatorTest() {
        MaxArrayDeque<Integer> md = new MaxArrayDeque<>(new IntegerComparator());
       md.addLast(1);
       md.addLast(2);
       md.addLast(3);
       md.addLast(4);
        assertEquals(4,md.max().intValue());
    }

}
