package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomATest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }
    @Test
    public void randomBTest() {
        BuggyAList<Integer> BL = new BuggyAList<>();
        AListNoResizing<Integer> AL = new AListNoResizing<>();
        int N = 1000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                BL.addLast(randVal);
                AL.addLast(randVal);
                assertEquals(AL.getLast(), BL.getLast());
            } else if (operationNumber == 1) {
                // size
                int Bsize = BL.size();
                int Asize = AL.size();
                assertEquals(AL.size(), BL.size());
            } else if (operationNumber == 2) {
                if (AL.size() > 0) {
                    int Aval = AL.removeLast();
                    int Bval = BL.removeLast();
//                    System.out.println("removeLast(" + Aval + "," + Bval + ")");
//                    System.out.println("size: " + AL.size());
//                    System.out.println("size: " + BL.size());
                    assertEquals(Aval, Bval);
                    assertEquals(AL.size(), BL.size());
                }
            }
        }
    }
    @Test
    public void removeLastTest() {
        BuggyAList<Integer> BL = new BuggyAList<>();
        BL.addLast(50);
        BL.addLast(50);
        BL.addLast(50);
        BL.addLast(50);
        BL.addLast(50);
        BL.removeLast();
        BL.removeLast();
        BL.removeLast();
        BL.removeLast();
        System.out.println(BL.size());
    }
    @Test
    public void removeOnlyTest() {
        BuggyAList<Integer> BL = new BuggyAList<>();
        BL.removeLast();
        BL.removeLast();
        BL.removeLast();
        for (int i = 0; i < 10000; i++) {
            BL.addLast(i);
        }
        for (int i = 0; i < 9999; i++) {
            BL.removeLast();
        }
        System.out.println(BL.size());
    }
    @Test
    public void getOutOfBoundsTest() {
        BuggyAList<Integer> BL = new BuggyAList<>();
        BL.get(200);
    }


}
