/*

Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
 the range of real numbers x such that start <= x < end.

A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking.
Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation:
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
Note:

The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

 */
package segmenttree.medium;

/**
 * Created by poorvank.b on 20/11/17.
 */
public class MyCalender2 {

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
    public MyCalender2() {
        root = new SegmentNode(0,new int[]{0,1000000000});
    }

    /*

    Check if the query range is invalid or out of range with respect to current segment -- if so, simply return 0.

    Check if the query range covers current segment -- if so, simply return the k value of current segment node.

    Normalize the segment tree node -- the node may have been marked as lazy from previous steps,
    so we need to remove the laziness in order to see the most recent values.

    Recurse to the left and right subtrees -- simply call the query function on the two child nodes of current segment node with the same query range [i, j].

    Return the combined results of the left and right subtrees -- in this case, it will be the larger one of the two, since we need the maximum integer k.
     */

    private int query(SegmentNode root,int start,int end) {

        if(start>end || root==null || root.range[1]<start || root.range[0]>end) {
            return 0;
        }

        if(start<=root.range[0] && end>=root.range[1]) {
            return root.bookingCount;
        }
        normalise(root);

        return Math.max(query(root.left,start,end),query(root.right,start,end));
    }

    /*

    Check if the query range is invalid or out of range with respect to current segment -- if so, simply return.

    Check if the query range covers current segment -- if so, update the property and lazy fields of current segment node, then return.

    Normalize the segment tree node -- the node may have been marked as lazy from previous steps, so we need to remove the laziness in order
    to avoid overwriting prior updates.

    Recurse to the left and right subtrees -- simply call the update function on the two child nodes of current segment node with the same query range [i, j].

    Propagate the results of the left and right subtrees back to the parent node -- in this case,
    the k value of the parent node will be set to the larger one of the two subtree nodes.

     */
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

    }

    /*

    Push down the laziness to the child nodes
        1a. First make sure current segment node is not a leaf node (a leaf node has l == r)
        1b. If the two child nodes are null, we initialize them with the value of current node
        1c. Otherwise we simply update their propery and lazy fields (by adding the lazy field of current node).

    Reset the laziness of current segment node so as to normalize it (by resetting its lazy field to 0).

     */
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

    public boolean book(int start, int end) {

        int k = query(root,start,end-1);
        if(k>=2) {
            return false;
        }

        update(root,start,end-1,1);
        return true;
    }

    public static void main(String[] args) {
        MyCalender2 myCalender = new MyCalender2();
        myCalender.book(10,20);
        myCalender.book(50,60);
        myCalender.book(10,15);
        myCalender.book(5,15);
    }

}
/*

SEGMENT TREE

TreeMap Sol:

TreeMap<Integer,Integer> map;
    public MyCalender2() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int booked = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            booked += entry.getValue();
            if (booked == 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }

For each time point, store how the number of booked events changes. For each booking attempt, book it and undo the booking if it
causes a triple booking (as determined by going through the time line, keeping track of the number of booked events).

Time Complexity: O(N^2), where N is the number of events booked.



This is more efficient:

for (Map.Entry entry : map.entrySet()) {
    Object key = entry.getKey();
    Object value = entry.getValue();
}
than:

for (Object key : map.keySet()) {
    Object value = map.get(key);
}
Because in the second case, for every key in the keySet the map.get() method is called,
which - in the case of a HashMap - requires that the hashCode() and equals() methods of the key object be evaluated in order to find the associated value.
In the first case that extra work is eliminated.

Edit: This is even worse if you consider a TreeMap, where a call to get is O(log2(n)), i.e.
the comparator for will may need to run log2(n) times (n = size of the Map) before finding the associated value.

 */