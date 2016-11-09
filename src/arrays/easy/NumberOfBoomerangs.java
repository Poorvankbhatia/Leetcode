/*

Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that
the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range
[-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

 */
package arrays.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 07/11/16.
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {

        int boomerangs = 0;
        for(int i=0;i<points.length;i++) {
            Map<Integer, Integer> distances = new HashMap<>();
            for(int j=0;j<points.length;j++) {
                if(i==j) continue;
                int dx = points[i][0]-points[j][0];
                int dy = points[i][1]-points[j][1];
                int dSquare = (dx*dx)+(dy*dy);
                if(distances.containsKey(dSquare)) {
                    distances.put(dSquare, distances.get(dSquare)+1);
                } else {
                    distances.put(dSquare, 1);
                }
            }

            for(Integer radius: distances.keySet()) {
                int numberOfPoints = distances.get(radius);
                if(numberOfPoints>1) {
                    boomerangs+=numberOfPoints*(numberOfPoints-1);
                }
            }
        }
        return boomerangs;

    }

}
