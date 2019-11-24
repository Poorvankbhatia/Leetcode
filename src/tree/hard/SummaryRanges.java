/*

Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

 */
package tree.hard;

import java.util.*;

/**
 * Created by poorvank.b on 25/11/17.
 */
public class SummaryRanges {

    TreeMap<Integer,Integer> map;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        Integer floor = map.floorKey(val);
        int start=val,end=val;
        if(floor!=null) {
            if(map.get(floor)>=val) {
                return;
            } else if(map.get(floor)==val-1) {
                start = floor;
                map.remove(floor);
            }
        }
        Integer ceil = map.ceilingKey(val);
        if(ceil!=null) {
            if(ceil==val+1) {
                end = map.get(ceil);
                map.remove(ceil);
            }
        }
        map.put(start,end);
    }

    public int[][] getIntervals() {
        int[][] result = new int[map.size()][2];
        int i=0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            result[i] = new int[]{entry.getKey(),entry.getValue()};
            i++;
        }
        return result;
    }

}

/*

We can store the interval in an array and each time iterator over the array and merge the new value to an existing interval.
This takes time O(n). If there are a lot of merges, we want to do it in log(n).

We can solve this problem using a tree set. The floor() method returns the greatest element in this set less than or equal to
the given element, or null if there is no such element. The higher() method returns the least element in this set strictly greater than
the given element, or null if there is no such element. Note: we use higher() instead of ceiling() to exclude the given element.

 */