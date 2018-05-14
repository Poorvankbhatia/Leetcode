/*

Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i,
 which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i.
If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

 */

package hashing.medium;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 29/03/17.
 */
public class FindRightInterval {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }



    public int[] findRightInterval(Interval[] intervals) {

        int[] result = new int[intervals.length];

        if(intervals.length <= 1) {
            return new int[]{-1};
        }

        TreeMap<Integer,Integer> map = new TreeMap<>();

        for (int i=0;i<intervals.length;i++) {
            map.put(intervals[i].start,i);
        }

        for (int i=0;i<intervals.length;i++) {
            Integer key = map.ceilingKey(intervals[i].end);
            result[i] = key!=null ?map.get(key) : -1;
        }

        return result;

    }


    public static void main(String[] args) {

        Interval[] intervals = {new Interval(3,4),new Interval(2,3),new Interval(1,2)};
        System.out.print(Arrays.toString(new FindRightInterval().findRightInterval(intervals)));

    }

}

/*

[  [3,5],[2,3], [1,2],[3,4] ]

output should be : [-1,0,1,-1]

We get: [-1,3,1,-1]

 */