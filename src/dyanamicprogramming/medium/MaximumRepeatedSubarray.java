/*

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation:
The repeated subarray with maximum length is [3, 2, 1].

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 30/10/17.
 */
public class MaximumRepeatedSubarray {

    public int findLength(int[] A, int[] B) {

        if(A.length==0 || B.length==0) {
            return 0;
        }

        int[][] dp = new int[A.length+1][B.length+1];

        int result = 0;

        for (int i=0;i<=A.length;i++) {
            for (int j=0;j<=B.length;j++) {
                if(i==0 || j==0) {
                    dp[i][j] = 0;
                }
                else if(A[i-1]==B[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                    if(result<dp[i][j]) {
                        result = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] A = new int[]{0,1,1};
        int[] B = new int[]{1,0,1,0,1};

        System.out.println(new MaximumRepeatedSubarray().findLength(A,B));
    }

}

/*

Same as longest common substring

Time Complexity: O(M*N) where M, N are the lengths of A, B.

 */