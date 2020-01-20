/*

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

 */
package greedy.medium;

import java.util.Arrays;
import java.util.Comparator;

/*
  Created by poorvank on 30/11/16.
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); //Sort the intervals by their start time.
        int end=intervals[0][1];
        int count = 1;
        for (int i=1;i<intervals.length;i++) {
            if(intervals[i][0]>=end) {
                count++;
                end = intervals[i][1]; // If two intervals overlap, the interval with larger end
                // time will be removed so as to have as little impact on subsequent intervals as possible.
            } else {
                end = Math.min(end,intervals[i][1]);
            }
        }
        return intervals.length-count;
    }

}

/*

rder intervals by start point.
Record the end of the last valid interval.
For each interval, if is start point is >= the end of the last valid interval, increment the count of valid intervals,
and move the end point to the end of the current interval. Otherwise just set the new end point to the minimum between the two overlapping intervals.

Return the difference between the number of intervals in the input array and the number of valid intervals you found in the previous way.

 */
