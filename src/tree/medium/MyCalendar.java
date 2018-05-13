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

    private class BookingNode {
        BookingNode left;
        BookingNode right;
        Integer max;
        Interval interval;

        public BookingNode(Interval interval) {
            this.interval = interval;
            this.max = interval.high;
        }
    }

    BookingNode root;

    public MyCalendar() {
        root = null;
    }

    public boolean book(int start, int end) {
        Interval interval = new Interval(start,end);
        if(root!=null) {
            if(shouldInsert(root,interval)) {
                return false;
            }
        }
        root = insertBooking(root,interval);
        System.out.println("Inserted - " + interval.toString() + " max " + root.max);
        return true;
    }

    private boolean checkIntervalOverlapping(Interval i1, Interval i2) {
        return i1.low<i2.high && i1.high>i2.low;
    }

    private boolean shouldInsert(BookingNode root, Interval interval) {

        if(root==null) {
            return false;
        }

        if(checkIntervalOverlapping(root.interval,interval)) {
            return true;
        } else if(root.left!=null && root.left.max>=interval.low) {
            return shouldInsert(root.left,interval);
        } else {
            return shouldInsert(root.right,interval);
        }

    }

    private BookingNode insertBooking(BookingNode rootNode,Interval i) {
        if(rootNode==null) {
            rootNode = new BookingNode(i);
        } else if(i.low<rootNode.interval.low) {
            rootNode.left = insertBooking(rootNode.left,i);
        } else {
            rootNode.right = insertBooking(rootNode.right,i);
        }

        if(rootNode.max<i.high) {
            rootNode.max = i.high;
        }

        return rootNode;
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