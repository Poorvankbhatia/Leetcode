/*

There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden.
Your job is to fence the entire garden using the minimum length of rope as it is expensive.
The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of
trees which are exactly located on the fence perimeter.


Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]

Input: [[1,2],[2,2],[4,2]]
Output: [[1,2],[2,2],[4,2]]

 */

package math.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by poorvank on 15/05/17.
 */
public class ErectTheFence {

    private class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public List<Point> outerTrees(Point[] points) {

        int n = points.length;

        // Finding the point having left most x coordinate.
        Point first = points[0];
        int startIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < first.x) {
                first = points[i];
                startIndex = i;
            }
        }

        int p = startIndex,q;

        Set<Point> set = new HashSet<>();
        do {
            set.add(points[p]);
            q = (p+1)%n;
            for (int i=0;i<n;i++) {
                if(findOrientation(points[p],points[i],points[q])<0) {
                    q = i;
                }
            }

            // checking for all the other points for collinear .
            for (int i = 0; i < points.length; i++) {
                if (i == p) continue;
                int cross = findOrientation(points[p], points[i], points[q]);
                if (cross == 0) {
                    set.add(points[i]);
                }
            }

            p=q;

        } while (p!=startIndex);



        return new ArrayList<>(set);
    }


    private int findOrientation(Point p,Point q,Point r) {
        return ((q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y));
    }

}

// Convex hull jarvis algorithm implementation.