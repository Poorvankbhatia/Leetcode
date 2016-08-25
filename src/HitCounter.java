/*

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the
system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);

 */
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 20/08/16.
 */
public class HitCounter {

    private static Queue<Integer> queue = new LinkedList<>();

    /*
      Every second a requests comes we store that second
     */
    public static void hit(int second) {
       queue.add(second);
    }

    /*
    Since hits are made in chronological order
     */
    public static int getHits(int second) {
        while (!queue.isEmpty() && second-queue.peek()>=300) {
            queue.poll();
        }
        return queue.size();
    }

    public static void main(String[] args) {

        HitCounter.hit(1);
        HitCounter.hit(2);
        HitCounter.hit(3);
        HitCounter.hit(300);
        System.out.println(HitCounter.getHits(300));
        System.out.println(HitCounter.getHits(301));

    }

}
