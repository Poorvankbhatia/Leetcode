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


Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

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

/*

1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?

We can maintain an integer array of length 100 to store the count of each number along with a total count.
Then, we can iterate over the array to find the middle value to get our median.

Time and space complexity would be O(100) = O(1).

2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

In this case, we need an integer array of length 100 and a hashmap for these numbers that are not in [0,100].


class MedianFinder {
        int A[] = new int[100], n = 0;

        public void addNum(int num) {
            A[num]++;
            n++;
        }

        public double findMedian() {
            int count = 0, i = 0;
            while (count < n/2) count += A[i++];
            int j = i;
            while (count < n/2+1) count += A[j++];
            return (n%2 == 1) ? j-1 : (i+j-2) / 2.0;
        }
    }
Insert - O(1), Find O(1), Space Complexity O(1)

 */
