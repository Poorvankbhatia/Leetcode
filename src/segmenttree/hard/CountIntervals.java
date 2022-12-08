/*

Given an empty set of intervals, implement a data structure that can:

Add an interval to the set of intervals.
Count the number of integers that are present in at least one interval.
Implement the CountIntervals class:

CountIntervals() Initializes the object with an empty set of intervals.
void add(int left, int right) Adds the interval [left, right] to the set of intervals.
int count() Returns the number of integers that are present in at least one interval.
Note that an interval [left, right] denotes all the integers x where left <= x <= right.



Example 1:

Input
["CountIntervals", "add", "add", "count", "add", "count"]
[[], [2, 3], [7, 10], [], [5, 8], []]
Output
[null, null, null, 6, null, 8]

Explanation
CountIntervals countIntervals = new CountIntervals(); // initialize the object with an empty set of intervals.
countIntervals.add(2, 3);  // add [2, 3] to the set of intervals.
countIntervals.add(7, 10); // add [7, 10] to the set of intervals.
countIntervals.count();    // return 6
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 7, 8, 9, and 10 are present in the interval [7, 10].
countIntervals.add(5, 8);  // add [5, 8] to the set of intervals.
countIntervals.count();    // return 8
                           // the integers 2 and 3 are present in the interval [2, 3].
                           // the integers 5 and 6 are present in the interval [5, 8].
                           // the integers 7 and 8 are present in the intervals [5, 8] and [7, 10].
                           // the integers 9 and 10 are present in the interval [7, 10].


Constraints:

1 <= left <= right <= 109
At most 105 calls in total will be made to add and count.
At least one call will be made to count.

 */
package segmenttree.hard;

public class CountIntervals {

    static class Node {
        int lower, upper, val;
        Node left, right;

        public Node(int lower, int upper, int val) {
            this.lower = lower;
            this.upper = upper;
            this.val = val;
        }
    }

    Node root;

    public CountIntervals() {
        root = new Node(0, 1000000000, 0);
    }

    private void setRange(Node node, int left, int right) {
        if (left <= node.lower && node.upper <= right) {
            node.val = node.upper - node.lower + 1;
            node.left = null;
            node.right = null;
            return;
        }
        int mid = (node.upper + node.lower) / 2;
        if (node.left == null && node.right == null) {
            node.left = new Node(node.lower, mid, node.val > 0 ? mid - node.lower + 1 : 0);
            node.right = new Node(mid + 1, node.upper, node.val > 0 ? node.upper - (mid + 1) + 1 : 0);
        }
        if (left <= mid)
            setRange(node.left, left, right);
        if (right > mid)
            setRange(node.right, left, right);
        node.val = node.left.val + node.right.val;
    }

    public void add(int left, int right) {
        setRange(root, left, right);
    }

    public int count() {
        return root.val;
    }

    public static void main(String[] args) {
        CountIntervals countIntervals = new CountIntervals(); // initialize the object with an empty set of intervals.
        countIntervals.add(571, 770);  // add [2, 3] to the set of intervals.
        countIntervals.add(920, 996); // add [7, 10] to the set of intervals.
        countIntervals.add(65, 512);  // add [5, 8] to the set of intervals.
        countIntervals.add(959, 959);  // add [5, 8] to the set of intervals.
        countIntervals.add(313, 330);
        countIntervals.add(473, 928);
        countIntervals.add(75, 561);
        System.out.println(countIntervals.count());    // return 8
    }

}

/*

Java treemap:

TreeMap<Integer, Integer> intervals;
int count;

public CountIntervals() {
    this.intervals = new TreeMap<>();
    this.count = 0;
}

public void add(int left, int right) {
	// start and end to record the merged interval
    int start = left;
    int end = right;
    int toAdd = end - start + 1;

    Map.Entry<Integer, Integer> floor = intervals.floorEntry(left);
    if (floor != null) {
        if (floor.getValue() >= end) {
            // the entire current interval has been covered by the floor, so no new numbers need to add, simply just return.
            return;
        }
        if (floor.getValue() >= start) {
            // the current interval has been partially covered by the floor, so deduct the overlapping numbers.
            toAdd -= floor.getValue() - start + 1;
            intervals.remove(floor.getKey());
            start = floor.getKey();
        }
    }

    Map.Entry<Integer, Integer> ceiling = intervals.ceilingEntry(left);
    // there could be multiple ceilings overlap with the current interval.
    // e.g. current [5, 20], ceilings: [6, 8], [10, 15], [18, 22]
    // we need to deduct the overlapping numbers properly
    while (ceiling != null && ceiling.getKey() <= end) {
        toAdd -= Math.min(end, ceiling.getValue()) - ceiling.getKey() + 1;
        intervals.remove(ceiling.getKey());
        end = Math.max(end, ceiling.getValue());
        ceiling = intervals.ceilingEntry(left);
    }

    // add the new numbers to the count/result.
    count += toAdd;
    // add the merged interval to treemap
    intervals.put(start, end);
}

public int count() {
    return count;
}

 */