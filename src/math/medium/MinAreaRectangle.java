/*

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points,
with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.


 */
package math.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 11/11/18.
 */
public class MinAreaRectangle {
    public int minAreaRect(int[][] points) {
        Set<String> s = new HashSet<>();
        for (int[] p : points) {
            s.add(p[0] +"_"+ p[1]);
        }
        int min = Integer.MAX_VALUE;
        int l = points.length;
        for (int i = 0; i < l; i++) {
            int[] p1 = points[i];
            for (int j = i + 1; j < l; j++) {
                int[] p2 = points[j];
                if (p1[0] == p2[0] || p1[1] == p2[1]) {
                    continue;
                }
                String need1 = p1[0] +"_" + p2[1];
                String need2 = p2[0] +"_" + p1[1];
                if (s.contains(need1) && s.contains(need2)) {
                    min = Math.min(Math.abs(p1[0]-p2[0]) * Math.abs(p1[1]-p2[1]), min);
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

/*

We just find out all the legal rectangles. It takes 2 points to define a rectangle.
One is in the lower left corner and the other is in the upper right corner. Then look at the other 2 points in the non-point set.
If it is, this can form a legal rectangle, enumerating the selection of 2 points in the lower left corner and the upper right corner.
Then judge the existence of the rectangle, if it exists, update MIN AREA

 */