/*

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2

 */

package heap.hard;

import java.util.PriorityQueue;

/**
 * Created by poorvank on 06/09/16.
 */
public class FindMedian {

    private PriorityQueue<Integer> max;
    private PriorityQueue<Integer> min;
    private double median;

    /** initialize your data structure here. */
    public FindMedian() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>((x, y)->y-x);
        median = 0.0;
    }

    public void addNum(int num) {
        if(median<num) {
            if(max.size()==min.size()) {
                min.add(num);
                median = min.peek();
            } else if(max.size()>min.size()) {
                min.add(num);
                median = (double) (max.peek()+min.peek())/2;
            } else {
                max.add(min.poll());
                min.add(num);
                median = (double) (max.peek()+min.peek())/2;
            }
        } else {
            if(max.size()==min.size()) {
                max.add(num);
                median = max.peek();
            } else if(max.size()>min.size()) {
                min.add(max.poll());
                max.add(num);
                median = (double) (max.peek()+min.peek())/2;
            } else {
                max.add(num);
                median = (double) (max.peek()+min.peek())/2;
            }
        }
    }

    public double findMedian() {
        return median;
    }

    public static void main(String[] args) {
        FindMedian sm = new FindMedian();
        Integer[] array = new Integer[]{2,3};

        for (Integer element : array) {
            sm.addNum(element);
            System.out.println("Current median in the system = " + sm.findMedian());
        }
    }

}
