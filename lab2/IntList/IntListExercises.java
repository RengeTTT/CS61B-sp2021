package IntList;

public class IntListExercises {

    /**
     * Part A: (Buggy) mutative method that adds a constant C to each
     * element of an IntList
     *
     * @param lst IntList from Lecture
     */
    /*
    *
    *       part A : head.rest!=null -> head != null
    *   如果终止条件是head.rest!=null,就是head->next == null 时停止，也就是说在head为最后一个node时，会终止addContent
    * */
    public static void addConstant(IntList lst, int c) {
        IntList head = lst;
        while (head != null) {
            head.first += c;
            head = head.rest;
        }
    }

    /**
     * Part B: Buggy method that sets node.first to zero if
     * the max value in the list starting at node has the same
     * first and last digit, for every node in L
     *
     * @param L IntList from Lecture
     */
    /*
    *   part b 通过断点调试发现在x>10时会忽略位数减少，因此只需要>=就可以通过test
    *
    *
    * */
    public static void setToZeroIfMaxFEL(IntList L) {
        IntList p = L;

        while (p != null) {
            int currentMax = max(p);
            boolean currentMaxAvailable = firstDigitEqualsLastDigit(currentMax);
            if (currentMaxAvailable) {
                p.first = 0;
            }
            p = p.rest;
        }
    }

    /** Returns the max value in the IntList starting at L. */
    public static int max(IntList L) {
        int max = L.first;
        IntList p = L.rest;
        while (p != null) {
            if (p.first > max) {
                max = p.first;
            }
            p = p.rest;
        }
        return max;
    }

    /** Returns true if the last digit of x is equal to
     *  the first digit of x.
     */
    public static boolean firstDigitEqualsLastDigit(int x) {
        int lastDigit = x % 10;
        while (x >= 10) {
            x = x / 10;
        }
        int firstDigit = x % 10;
        return firstDigit == lastDigit;
    }

    /**
     * Part C: (Buggy) mutative method that squares each prime
     * element of the IntList.
     *
     * @param lst IntList from Lecture
     * @return True if there was an update to the list
     */

    /*
    * part c
    * 如何找出bug样点？
    * 极端情况：全部都是质数，全部都不是质数
    * 发现全部都是质数时，质数平方只有一次
    *
    * return 语句中用的时 || 运算符，具有短路效应，在判断currElemIsPrime 为Ture时会停止对链表的递归
    * */
    public static boolean squarePrimes(IntList lst) {
        // Base Case: we have reached the end of the list
        if (lst == null) {
            return false;
        }

        boolean currElemIsPrime = Primes.isPrime(lst.first);

        if (currElemIsPrime) {
            lst.first *= lst.first;
        }

        return squarePrimes(lst.rest) || currElemIsPrime; // || 的短路效应
    }
}
