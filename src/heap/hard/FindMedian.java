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
