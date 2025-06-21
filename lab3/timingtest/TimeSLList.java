package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int[] N = new int[8];
        N[0] = 1000;
        for (int i = 1; i < N.length; i += 1) {
            N[i] = N[i - 1] * 2;
        }

        for (int n : N) {
            Ns.addLast(n);
            SLList<Integer> temp = new SLList<>();
            for (int i = 0; i < n; i++) {
                temp.addLast(i);
            }
            Stopwatch stopwatch = new Stopwatch();
            for (int i = 0; i < 10000; i++) {
                temp.getLast();
            }
            double time = stopwatch.elapsedTime();
            times.addLast(time);
            opCounts.addLast(10000);
        }

        printTimingTable(Ns, times, opCounts);
    }

}
