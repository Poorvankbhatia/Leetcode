/*

You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c,
return the shortest distance between the given index i and the target color c. If there is no solution return -1.



Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation:
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.


Constraints:

1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3

 */
package binarysearch.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestDistanceToTarget {

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            map.putIfAbsent(colors[i], new ArrayList<>());
            map.get(colors[i]).add(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            List<Integer> list = map.get(query[1]);
            if (list != null && list.size() > 0) {
                int dis = nearest(0, list.size() - 1, list, query[0]);
                result.add(Math.abs(list.get(dis) - query[0]));
            } else {
                result.add(-1);
            }
        }

        return result;

    }

    private int nearest(int start, int end, List<Integer> list, int target) {

        if (list.get(start) >= target) {
            return start;
        } else if (list.get(end) <= target) {
            return end;
        } else if (end - start == 1) {
            if (Math.abs(list.get(end) - target) > Math.abs(list.get(start) - target)) {
                return start;
            } else {
                return end;
            }
        }

        int mid = (start) + (end - start) / 2;

        if (list.get(mid) == target) {
            return mid;
        } else if (list.get(mid) > target) {
            return nearest(start, mid, list, target);
        } else {
            return nearest(mid, end, list, target);
        }

    }

    public static void main(String[] args) {
        int[] colors = new int[]{1, 1, 2, 1, 3, 2, 2, 3, 3, 2, 2, 3, 2, 3, 2, 1, 2, 3, 1, 2};
        int[][] queries = new int[][]{
                {6, 1}
        };
        System.out.println(new ShortestDistanceToTarget().shortestDistanceColor(colors, queries));
    }

}

/*

O(n) Solution:

The DP definition is left[color][index] represents the minimum distance of the color to the left of the index index.
Similarly, right[color][index] represents the minimum distance of the color color to the right of the index index.
Note that we also include the current element in the DP definition.

public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
    int n = colors.length;
    int[][] lft = new int[4][n];
    int[][] rgt = new int[4][n];
    for (int i = 1; i < 4; i++) {
        Arrays.fill(lft[i], -1);
        Arrays.fill(rgt[i], -1);
    }
    computeLft(colors, lft);
    computeRgt(colors, rgt);
    List<Integer> res = new ArrayList<>();
    for (int[] q : queries) {
        int i = q[0];
        int c = q[1];
        int l = lft[c][i];
        int r = rgt[c][i];
        res.add(l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r));
    }
    return res;
}

private void computeLft(int[] colors, int[][] res) {
    for (int i = 1; i < 4; i++) {
        for (int j = 0; j < colors.length; j++) {
            if (colors[j] == i) {
                res[i][j] = 0;
            } else if (j > 0 && res[i][j - 1] != -1) {
                res[i][j] = res[i][j - 1] + 1;
            }
        }
    }
}

private void computeRgt(int[] colors, int[][] res) {
    for (int i = 1; i < 4; i++) {
        for (int j = colors.length - 1; j >= 0; j--) {
            if (colors[j] == i) {
                res[i][j] = 0;
            } else if (j < colors.length - 1 && res[i][j + 1] != -1) {
                res[i][j] = res[i][j + 1] + 1;
            }
        }
    }
}

 */
