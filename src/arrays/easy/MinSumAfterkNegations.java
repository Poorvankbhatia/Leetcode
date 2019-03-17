/*

Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i],
and we repeat this process K times in total.  (We may choose the same index i multiple times.)

Return the largest possible sum of the array after modifying it in this way.



Example 1:

Input: A = [4,2,3], K = 1s
Output: 5
Explanation: Choose indices (1,) and A becomes [4,-2,3].
Example 2:

Input: A = [3,-1,0,2], K = 3
Output: 6
Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
Example 3:

Input: A = [2,-3,-1,5,-4], K = 2
Output: 13
Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].


Note:

1 <= A.length <= 10000
1 <= K <= 10000
-100 <= A[i] <= 100

 */
package arrays.easy;

import java.util.Arrays;

public class MinSumAfterkNegations {

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        for (int i = 0; K > 0 && i < A.length && A[i] < 0; ++i, --K)
            A[i] = -A[i];
        int res = 0, min = Integer.MAX_VALUE;
        for (int a : A) {
            res += a;
            min = Math.min(min, a);
        }
        return res - (K % 2) * min * 2;
    }

}


/*

public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int i=0;
        while(A[i]<0 && K>0) {
            A[i]*=-1;
            K--;
            i++;
        }
        Arrays.sort(A);
        i=0;
        if(K>0 && K%2!=0) {
            A[i]*=-1;
        }
        return sum(A);
    }

    private int sum(int[] A) {
        int sum=0;
        for(int i :A) {
            sum +=i;
        }
        return sum;
    }

 */