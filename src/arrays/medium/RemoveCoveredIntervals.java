/*

Given a list of intervals, remove all intervals that are covered by another interval in the list.
Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.



Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.


Constraints:

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j

 */
package arrays.medium;

import java.util.Arrays;

public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]!=0?a[0]-b[0]:b[1]-a[1]));
        int n = intervals.length;
        int[] first = intervals[0];
        int count=1;
        for(int i=1;i<n;i++) {
            if(!covers(first,intervals[i])) {
                first = intervals[i];
                count++;
            }
        }
        return count;
    }

    private boolean covers(int[] a,int[] b) {
        return a[0]<=b[0] && a[1]>=b[1];
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,4},
                {1,6}
        };
        System.out.println(new RemoveCoveredIntervals().removeCoveredIntervals(a));
    }

}

/*

Sort the intervals & compare.

 */