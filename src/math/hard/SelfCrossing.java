/*

You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north,
then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move your
direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:
Given x =
[2, 1, 1, 2]
,
┌───┐
│   │
└───┼──>
    │

Return true (self crossing)
Example 2:
Given x =
[1, 2, 3, 4]
,
┌──────┐
│      │
│
│
└────────────>

Return false (not self crossing)
Example 3:
Given x =
[1, 1, 1, 1]
,
┌───┐
│   │
└───┼>

Return true (self crossing)

 */
package math.hard;

/**
 * Created by poorvank.b on 26/03/17.
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] x) {

        if(x==null || x.length==0 || x.length<=3) {
            return false;
        }

        for (int i=3;i<x.length;i++) {

            if(x[i] >= x[i-2] && x[i-1] <= x[i-3])
                return true;  //Fourth line crosses first line and onward
            if(i >=4) {
                if(x[i-1] == x[i-3] && x[i] + x[i-4] >= x[i-2])
                    return true; // Fifth line meets first line and onward
            }
            if(i >=5) {
                if(x[i-2] - x[i-4] >= 0 && x[i] >= x[i-2] - x[i-4] && x[i-1] >= x[i-3] - x[i-5] && x[i-1] <= x[i-3])
                    return true;  // Sixth line crosses first line and onward
            }


        }

        return false;

    }



    public static void main(String[] args) {
        int[] x = new int[]{2,3,4,2,1,1,1};
        System.out.print(new SelfCrossing().isSelfCrossing(x));

    }

}
