/*
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
Check if these points make a straight line in the XY plane.

Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true

Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false


Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
 */
package math.easy;

public class CheckIfStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        for(int end=0;end<=n-3;end++) {
            int[] c1 = coordinates[end];
            int[] c2 = coordinates[end+1];
            int[] c3 = coordinates[end+2];
            if(!isCollinear(c1[0],c1[1],c2[0],c2[1],c3[0],c3[1])) {
                return false;
            }
        }
        return true;
    }

    private boolean isCollinear(int x1, int y1, int x2,int y2, int x3, int y3) {
        int result = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
        return result==0;
    }
}

/*

Three points lie on the straight line if the area formed by the triangle of these three points is zero.
Formula for area of triangle is :
0.5 * (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2))

 */
