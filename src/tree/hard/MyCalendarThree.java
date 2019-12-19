/*

Implement a MyCalendarThree class to store your events. A new event can always be added.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
the range of real numbers x such that start <= x < end.

A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)

For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.

Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
Example 1:
MyCalendarThree();
MyCalendarThree.book(10, 20); // returns 1
MyCalendarThree.book(50, 60); // returns 1
MyCalendarThree.book(10, 40); // returns 2
MyCalendarThree.book(5, 15); // returns 3
MyCalendarThree.book(5, 10); // returns 3
MyCalendarThree.book(25, 55); // returns 3
Explanation:
The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
The remaining events cause the maximum K-booking to be only a 3-booking.
Note that the last event locally causes a 2-booking, but the answer is still 3 because
eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
Note:

The number of calls to MyCalendarThree.book per test case will be at most 400.
In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].

 */
package tree.hard;

import java.util.Arrays;

/**
 * Created by poorvank on 30/11/17.
 */
public class MyCalendarThree {

    private class SegmentNode {
        private SegmentNode left;
        private SegmentNode right;
        private int bookingCount;
        private int[] range;
        private int lazy;

        SegmentNode(int bookingCount, int[] range) {
            this.left = null;
            this.right = null;
            this.bookingCount = bookingCount;
            this.range = range;
            this.lazy = 0;
        }
    }

    SegmentNode root;
    private int max=0;
    public MyCalendarThree() {
        root = new SegmentNode(0,new int[]{0,1000000000});
    }

    private void update(SegmentNode node, int start,int end,int val) {

        if(start>end || node==null || start>node.range[1] || end<node.range[0]) {
            return;
        }

        if(start<=node.range[0] && node.range[1]<=end) {
            node.bookingCount+=val;
            node.lazy+=val;
            return;
        }

        normalise(node);

        update(node.left,start,end,val);
        update(node.right,start,end,val);

        node.bookingCount = Math.max(node.left.bookingCount,node.right.bookingCount);
        max = Math.max(node.bookingCount,max);
        //System.out.println(Arrays.toString(node.range));
    }


    private void normalise(SegmentNode node) {
        if(node.range[0]<node.range[1]) {
            int mid = node.range[0] + (node.range[1]-node.range[0])/2;
            if(node.left==null && node.right==null) {
                node.left = new SegmentNode(node.bookingCount,new int[]{node.range[0],mid});
                node.right = new SegmentNode(node.bookingCount,new int[]{mid+1,node.range[1]});
            } else if(node.lazy>0) {
                node.left.lazy+=node.lazy;
                node.left.bookingCount+=node.lazy;
                node.right.lazy+=node.lazy;
                node.right.bookingCount+=node.lazy;
            }
        }
        node.lazy=0;
    }

    public int book(int start, int end) {
        update(root,start,end-1,1);
        return max;
    }

    public static void main(String[] args) {
        MyCalendarThree a =new MyCalendarThree();
        System.out.println(a.book(10,20));
        System.out.println(a.book(50,60));
        System.out.println(a.book(10,40));
        System.out.println(a.book(5,15));
        System.out.println(a.book(5,10));
        System.out.println(a.book(25,55));
    }

}

/*

SEGMENT TREE

Time complexity: for each call of the function book, we need to do at most one query and one update,
both of which can be done in logd time, where d is the maximum range allowed for all events (in this case d = 10^9).
Therefore, for n calls of the book function, the total time complexity will be O(nlogd).

Space complexity: in the worst case, the segment tree can be a full binary tree with 2d nodes. However, this is very
unlikely as it would require a total of d calls of the book function, each with an event of range 1. For n calls of the book function,
the average space cost is roughly O(nlogd).

TREE MAP SOL:

private static TreeMap<Integer, Integer> times = new TreeMap<>();
    public int book(int s, int e) {
        times.put(s, times.getOrDefault(s, 0) + 1); // 1 new event will be starting at times[s]
        times.put(e, times.getOrDefault(e, 0) - 1); // 1 new event will be ending at times[e];
        int ongoing = 0, k = 0;
        for (int value : times.values()) {
            ongoing+=value;
            k = Math.max(k, ongoing);
        }
        return k;
    }

This is to find the maximum number of concurrent ongoing event at any time.

We can log the start & end of each event on the timeline, each start add a new ongoing event at that time,
each end terminate an ongoing event. Then we can scan the timeline to figure out the maximum number of ongoing event at any time.

O(N^2), where N is the number of events booked. For each new event, we traverse delta in O(N) time.



 */