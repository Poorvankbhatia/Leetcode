/*

Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.

We remove the intersections between any interval in intervals and the interval toBeRemoved.

Return a sorted list of intervals after all such removals.



Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]


Constraints:

1 <= intervals.length <= 10^4
-10^9 <= intervals[i][0] < intervals[i][1] <= 10^9

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

public class RemoveInterval {

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] interval : intervals) {
            List<Integer> list = new ArrayList<>();
            int[] intersection = getIntersection(interval, toBeRemoved);
            if (intersection == null) { // No Intersection
                list.add(interval[0]);
                list.add(interval[1]);
            } else if (intersection[0] == interval[0] && intersection[1] == interval[1]) { // interval is a subset of toBeRemoved.
                continue;
            } else if (intersection[0] == interval[0]) {
                list.add(intersection[1]);
                list.add(interval[1]);
            } else if (intersection[1] == interval[1]) {
                list.add(interval[0]);
                list.add(intersection[0]);
            } else { //toBeRemoved is a subset of interval
                list.add(interval[0]);
                list.add(intersection[0]);
                result.add(list);
                list = new ArrayList<>();
                list.add(intersection[1]);
                list.add(interval[1]);
            }
            result.add(list);
        }
        return result;
    }

    private int[] getIntersection(int[] a,int[] b) {
        if(b[0]>a[1] || a[0]>b[1]) {
            return null;
        }
        return new int[]{Math.max(a[0],b[0]),Math.min(a[1],b[1])};
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {-5,-4},
                {-3,-2},
                {1,2},
                {3,5},
                {8,9}
        };
        System.out.println(new RemoveInterval().removeInterval(a,new int[]{-1,4}));

    }


}

/*

Another Sol:

public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] i : intervals) {
            if (i[1] <= toBeRemoved[0] || i[0] >= toBeRemoved[1]) { // no removal.
                ans.add(Arrays.asList(i[0], i[1]));
            }else { // i[1] > toBeRemoved[0] && i[0] < toBeRemoved[1].
                if(i[0] < toBeRemoved[0])
                    ans.add(Arrays.asList(i[0], toBeRemoved[0]));
                if (i[1] > toBeRemoved[1])
                    ans.add(Arrays.asList(toBeRemoved[1], i[1]));
            }
        }
        return ans;
    }

 */