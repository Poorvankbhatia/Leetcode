/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.



Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1
 */
package arrays.medium;

public class MaximumConsecutiveOnes3 {

    public int longestOnes(int[] A, int K) {
        int n = A.length;
        int zeroCount = 0;
        int len = 0;
        int start=0,end=0;
        for(end=0;end<n;end++) {
            if(A[end]==0) {
                zeroCount++;
            }
            if(zeroCount<=K) {
                len = Math.max(len,end-start+1);
            }
            while(zeroCount>K) {
                if(A[start]==0) {
                    zeroCount--;
                }
                start++;
            }
        }
        return len;
    }

}
