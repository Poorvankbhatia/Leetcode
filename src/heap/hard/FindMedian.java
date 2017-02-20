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

import heap.MaxPriorityQueue;
import heap.MinPriorityQueue;

/**
 * Created by poorvank on 06/09/16.
 */
public class FindMedian {

    private MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>();
    private MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>();
    private double median = 0;

    // Adds a number into the data structure.
    public void addNum(int num) {
        int sizeDiff = maxPriorityQueue.getSize()-minPriorityQueue.getSize();
        if(sizeDiff==1) {
            if(num>median) {
                minPriorityQueue.insert(num);
            } else {
                minPriorityQueue.insert(maxPriorityQueue.deleteMax());
                maxPriorityQueue.insert(num);
            }
            median = (double) (maxPriorityQueue.getMaximumElement()+minPriorityQueue.getMinimumElement())/2;
        } else if(sizeDiff==0) {
            if(num>median) {
                minPriorityQueue.insert(num);
                median = minPriorityQueue.getMinimumElement();
            } else {
                maxPriorityQueue.insert(num);
                median = maxPriorityQueue.getMaximumElement();
            }
        } else if(sizeDiff==-1) {
            if(num>median) {
                maxPriorityQueue.insert(minPriorityQueue.deleteMin());
                minPriorityQueue.insert(num);
            } else {
                maxPriorityQueue.insert(num);
            }
            median = (double) (maxPriorityQueue.getMaximumElement()+minPriorityQueue.getMinimumElement())/2;
        }
    }

    // Returns the median of current data stream
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
