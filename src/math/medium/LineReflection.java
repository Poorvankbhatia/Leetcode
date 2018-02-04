/*

Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflects the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

 */
package math.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 29/01/18.
 */
public class LineReflection {

    public boolean isReflected(int[][] points) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        Set<String> set = new HashSet<>();

        for (int[] point : points) {
            min = Math.min(point[0],min);
            max = Math.max(point[0],max);
            set.add(point[0]+"_"+point[1]);
        }

        int sum = max+min;

        for (int[] point : points) {
            String other = sum-point[0]+"_"+point[1];
            if(!set.contains(other)) {
                return false;
            }
        }

        return true;

    }

}

/*

For this problem, we first find the smallest and largest x-value for all points and get the line's x-axis is (minX + maxX)
 then for each point, check if each point has a reflection points in the set.

 */