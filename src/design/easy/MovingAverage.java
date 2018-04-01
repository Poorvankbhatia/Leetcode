/*

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

class MovingAverage {

  public MovingAverage(int size) {

  }

  public double next(int val) {

  }
}
For example,

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

 */
package design.easy;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by poorvank.b on 17/12/16.
 */
public class MovingAverage {

    private Queue<Integer> queue;
    private int sum;
    private int size;

    public MovingAverage(int size) {
        queue = new ArrayDeque<>();
        sum = 0;
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        if(queue.size()>size) {
            sum -= queue.poll();
        }

        sum += val;
        return (double)sum/queue.size();

    }

}

/*

 Basically we simulate the sliding window by maintaining a queue of size size, and a running sum of all elements in the window.

 public class MovingAvg {

    int[] q;  // a circular queue of size N
    int head; //queue head
    int tail; //queue tail
    int size; // queue size
    int sum;

    public MovingAvg(int N) {
        q = new int[N];
    }

    //@param num - the next number from data stream
    //@return - new average with num included and expired number excluded
    public double getAverage(int num) {
        double avg = 0;
        sum += num;
        if(size == q.length) {
            sum -= q[head];
            head = (head + 1) % q.length;
        } else {
            size++;
        }
        q[tail] = num;
        tail = (tail + 1) % q.length;
        return 1.0 * sum / size;
    }
}

 */