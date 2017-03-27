/*

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

 */
package math.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 22/02/17.
 */
public class MaximumPointsInLine {


    class Point {
        int x;
        int y;
    }


    public int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlappingPoints = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlappingPoints++;
                    continue;
                }
                int gcd = generateGCD(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }

                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1);
                    }
                } else {
                    Map<Integer, Integer> m = new HashMap<>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max + overlappingPoints + 1);
        }
        return result;


    }

    private int generateGCD(int a, int b) {

        if (b == 0) return a;
        else return generateGCD(b, a % b);

    }


}

/*

For each point p, calculate its slope with other points and use a map to record how many points have same slope, by which we can find
out how many points are on same line with p as their one point. For each point keep doing the same thing and update the maximum number of
point count found so far.

Some things to note in implementation are:
1) if two point are (x1, y1) and (x2, y2) then their slope will be (y2 – y1) / (x2 – x1) which can be a double value and can cause precision
problems. To get rid of the precision problems, we treat slope as pair ((y2 – y1), (x2 – x1)) instead of ratio and reduce pair by their gcd
before inserting into map.
In below code points which are vertical or repeated are treated separately.

 */