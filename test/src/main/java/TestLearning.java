import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLearning {
    public static void main(String[] args) {
        String[] input = {"i","have","an","egg"};
        String[] expected = {"i","have","an","egg"};
        assert Arrays.equals(input, expected);
    }

    @Test
    public void test() {
        String[] input = {"i","have","an","egg"};
        String[] expected = {"an","egg","have","i"};
        assertArrayEquals(input, expected,"两个字符串似乎不太一样");
    }

}
