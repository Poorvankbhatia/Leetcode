/*

Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval
 [start, end), the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a
 double booking. Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation:
The first event can be booked.  The second can't because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.
Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 */
package tree.medium;

import tree.Interval;

/**
 * Created by poorvank.b on 19/11/17.
 */
public class MyCalendar {

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


    private SegmentNode root;
    public MyCalendar() {
        root = new SegmentNode(0,new int[]{0,1000000000});
    }

    private int query(int start,int end,SegmentNode node) {
        if(start>end || root==null || node.range[1]<start || node.range[0]>end) {
            return 0;
        }

        if(start<=node.range[0] && end>=node.range[1]) {
            return node.bookingCount;
        }
        normalise(node);

        return Math.max(query(start,end,node.left),query(start,end,node.right));
    }

    private void update(SegmentNode node, int start,int end,int val) {
        if(node ==null || start>end || start>node.range[1] || end<node.range[0]) {
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
        node.bookingCount=Math.max(node.left.bookingCount,node.right.bookingCount);

    }
    private void normalise(SegmentNode node) {
        if(node.range[0]<node.range[1]) {
            int mid = node.range[0] + (node.range[1]-node.range[0])/2;
            if(node.left==null || node.right==null) {
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

    public boolean book(int start, int end) {

        if(query(start,end-1,root)>0) {
            return false;
        }

        update(root,start,end-1,1);
        return true;
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(25,32));
        System.out.println(myCalendar.book(19,25));
    }

}

/*

Using Tree Map:

class MyCalendar {

    TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer floorKey = calendar.floorKey(start);
        if (floorKey != null && calendar.get(floorKey) > start) return false;
        Integer ceilingKey = calendar.ceilingKey(start);
        if (ceilingKey != null && ceilingKey < end) return false;

        calendar.put(start, end);
        return true;
    }
}


The floorKey(K key) method is used to return the greatest key less than or equal to the given key, or null if there is no such key.

we will have a TreeMap where the keys are the start of each interval, and the values are the ends of those intervals.
When inserting the interval [start, end), we check if there is a conflict on each side with neighboring intervals:
we would like calendar.get(prev)) <= start <= end <= next for the booking to be valid (or for prev or next to be null respectively.)


Time Complexity (Java): O(NlogN), where N is the number of events booked. For each new event, we search that the event is
 legal in O(logN) time, then insert it in O(1) time.


 */