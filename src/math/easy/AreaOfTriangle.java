/*

You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
Explanation:
The five points are show in the figure below. The red triangle is the largest.

 */
package math.easy;

/**
 * Created by poorvank.b on 08/04/18.
 */
public class AreaOfTriangle {

    public double largestTriangleArea(int[][] points) {

        if(points==null || points.length==0) {
            return 0;
        }

        double area = -1.0;


        for (int i=0;i<=points.length-3;i++) {
            for (int j=1;j<=points.length-2;j++) {
                for (int k=2;k<=points.length-1;k++) {
                    int Ax = points[i][0];
                    int Ay = points[i][1];
                    int Bx = points[j][0];
                    int By = points[j][1];
                    int Cx = points[k][0];
                    int Cy = points[k][1];

                    double currentArea = Math.abs(((Ax*(By-Cy)+Bx*(Cy-Ay)+Cx*(Ay-By))));
                    area = Math.max(area,currentArea/2);

                }
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[][] points = new int[][] {
                {1,0},{0,0},{0,1}
        };
        System.out.println(new AreaOfTriangle().largestTriangleArea(points));
    }

}

// Area of a triangle geven 3 points is - Math.abs(((Ax*(By-Cy)+Bx*(Cy-Ay)+Cx*(Ay-By))))/2
