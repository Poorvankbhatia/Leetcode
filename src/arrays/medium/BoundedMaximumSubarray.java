/*

We are given an array A of positive integers, and two positive integers L and R (L <= R).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least L and at most R.

Example :
Input:
A = [2, 1, 4, 3]
L = 2
R = 3
Output: 3
Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
Note:

L, R  and A[i] will be an integer in the range [0, 10^9].
The length of A will be in the range of [1, 50000].

 */
package arrays.medium;

/**
 * Created by poorvank.b on 10/03/18.
 */
public class BoundedMaximumSubarray {

    public int numSubarrayBoundedMax(int[] A, int L, int R) {

        if(A==null || A.length==0) {
            return 0;
        }

        int j=0,count=0,result=0;

        for (int i=0;i<A.length;i++) {
            if(A[i]>=L && A[i]<=R) {
                result+=i-j+1;
                count=i-j+1;
            } else if(A[i]<L) {
                result+=count;
            } else {
                j=i+1;
                count=0;
            }


        }

        return result;

    }

}
