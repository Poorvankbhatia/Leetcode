/*

Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.


 */
package math.medium;

/**
 * Created by poorvank on 21/05/17.
 */
public class ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        double d2 = distance(p1,p2);
        double d3 = distance(p1,p3);
        double d4 = distance(p1,p4);

        if(d2==0 || d3==0 || d4==0) {
            return false;
        }

        if (d2 == d3 && 2*d2 == d4) {
            double d = distance(p2, p4);
            return (d == distance(p3, p4) && d == d2);
        }
        if (d3 == d4 && 2*d3 == d2) {
            double d = distance(p2, p3);
            return (d == distance(p2, p4) && d == d3);
        }
        if (d2 == d4 && 2*d2 == d3) {
            double d = distance(p2, p3);
            return (d == distance(p3, p4) && d == d2);
        }

        return false;


    }


    private double distance(int[] p,int[] q) {
        return (p[0]-q[0])*(p[0]-q[0])+(p[1]-q[1])*(p[1]-q[1]);
    }

    public static void main(String[] args) {
        int[] p1 = {0,0};
        int[] p2 = {1,1};
        int[] p3 = {1,0};
        int[] p4 = {0,0};
        System.out.println(new ValidSquare().validSquare(p1,p2,p3,p4));
    }

}

/*

The idea is to pick any point and calculate its distance from rest of the points. Let the picked picked point be ‘p’. To form a square, distance
 of two points must be same from ‘p’, let this distance be d. The distance from one point must be different from that d and must be equal to √2
 times d. Let this point with different distance be ‘q’.
The above condition is not good enough as the the point with different distance can be on the other side. We also need to check that q is at same
 distance from 2 other points and this distance is same as d.

 */