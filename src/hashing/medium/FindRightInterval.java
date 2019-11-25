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
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 29/03/17.
 */
public class FindRightInterval {

    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> index = new HashMap<>();
        int k = 0;
        for (int[] interval : intervals) {
            index.put(interval[0], k++);
            map.put(interval[0], interval[1]);
        }
        int[] result = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            Integer ceil = map.ceilingKey(intervals[i][1]);
            if (ceil != null && index.get(ceil)!=i) {
                result[i] = index.get(ceil);
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

}

/*

Binary Search Sol:

public int[] findRightInterval(int[][] intervals) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<intervals.length;i++) {
            map.put(intervals[i][0],i);
        }
        Arrays.sort(intervals, (a,b)->(a[0]!=b[0]?a[0]-b[0]:a[1]-b[1]));
        int[] result = new int[intervals.length];
        for (int i=0;i<intervals.length;i++) {
            int correctIndex = map.get(intervals[i][0]);
            int next = bs(intervals[i],i+1,intervals.length-1,intervals);
            if(next!=-1) {
                result[correctIndex] = map.get(intervals[next][0]);
            } else {
                result[correctIndex]=-1;
            }
        }
        return result;
    }

    private int bs(int[] target,int start,int end,int[][] intervals) {
        while (start<end) {
            int mid = start+(end-start)/2;
            if(intervals[mid][0]>=target[1]) {
                end=mid;
            } else {
                start = mid+1;
            }
        }
        return start>=intervals.length?-1:intervals[start][0]>=target[1]?start:-1;
    }

 */