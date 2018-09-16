/*

Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.


Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000


 */

package arrays.medium;

/**
 * Created by poorvank.b on 16/09/18.
 */
public class SumOfSubArrayMinimums {

    private static int M=(int)1e9+7;

    public int sumSubarrayMins(int[] A) {
        if(A==null || A.length==0) {
            return 0;
        }

        int sum=0;
        for (int i=0;i<A.length;i++) {
            sum=(sum+countSubArrays(A,i)*A[i])%M;
        }

        return sum;

    }

    private int countSubArrays(int[] A,int i) {
        int left = 1;
        for (int j=i-1;j>=0;j--) {
            if(A[j]<=A[i]) {
                break;
            }
            left++;
        }
        int right = 1;
        for (int j=i+1;j<A.length;j++) {
            if(A[j]<A[i]) {
                break;
            }
            right++;
        }

        return left*right;
    }

}

/*

Stack soln :

public int sumSubarrayMins(int[] A) {
        int res = 0, n = A.length, mod = (int)1e9 + 7;
        int[] left = new int[n], right = new int[n];
        Stack<int[]> s1 = new Stack<>(), s2 = new Stack<>();
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > A[i])
                count += s1.pop()[1];
            s1.push(new int[] {A[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; --i) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] > A[i])
                count += s2.pop()[1];
            s2.push(new int[] {A[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; ++i)
            res = (res + A[i] * left[i] * right[i]) % mod;
        return res;
    }

 */