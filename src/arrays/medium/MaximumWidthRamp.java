/*

Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  The width of such a ramp is j - i.

Find the maximum width of a ramp in A.  If one doesn't exist, return 0.



Example 1:

Input: [6,0,8,2,1,5]
Output: 4
Explanation:
The maximum width ramp is achieved at (i, j) = (1, 5): A[1] = 0 and A[5] = 5.
Example 2:

Input: [9,8,1,0,1,9,4,0,4,1]
Output: 7
Explanation:
The maximum width ramp is achieved at (i, j) = (2, 9): A[2] = 1 and A[9] = 1.


Note:

2 <= A.length <= 50000
0 <= A[i] <= 50000

 */
package arrays.medium;

/**
 * Created by poorvank.b on 06/01/19.
 */
public class MaximumWidthRamp {

    public int maxWidthRamp(int[] A) {
        if(A==null || A.length==0) {
            return 0;
        }
        int n =A.length;
        int[] maxR = new int[n];
        maxR[n-1]=A[n-1];
        for(int i=n-2;i>=0;i--) {
            maxR[i]=Math.max(A[i],maxR[i+1]);
        }

        int i=0,j=0;
        int max=0;
        while(i<n && j<n) {
            if(A[i]<=maxR[j]) {
                max = Math.max(max,j-i);
                j++;
            } else {
                i++;
            }
        }

        return max;

    }

}
