/*

We are given an array A of N lowercase letter strings, all of the same length.

Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.

For example, if we have an array A = ["babca","bbazb"] and deletion indices {0, 1, 4}, then the final array after deletions is ["bc","az"].

Suppose we chose a set of deletion indices D such that after deletions, the final array has every element (row) in lexicographic order.

For clarity, A[0] is in lexicographic order (ie. A[0][0] <= A[0][1] <= ... <= A[0][A[0].length - 1]), A[1] is in lexicographic order
(ie. A[1][0] <= A[1][1] <= ... <= A[1][A[1].length - 1]), and so on.

Return the minimum possible value of D.length.



Example 1:

Input: ["babca","bbazb"]
Output: 3
Explanation: After deleting columns 0, 1, and 4, the final array is A = ["bc", "az"].
Both these rows are individually in lexicographic order (ie. A[0][0] <= A[0][1] and A[1][0] <= A[1][1]).
Note that A[0] > A[1] - the array A isn't necessarily in lexicographic order.
Example 2:

Input: ["edcba"]
Output: 4
Explanation: If we delete less than 4 columns, the only row won't be lexicographically sorted.
Example 3:

Input: ["ghi","def","abc"]
Output: 0
Explanation: All rows are already lexicographically sorted.


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100


 */
package dyanamicprogramming.hard;

import java.util.Arrays;

/**
 * Created by poorvank.b on 30/12/18.
 */
public class DeleteColumnsToMakeSorted3 {

    public int minDeletionSize(String[] A) {
        if(A==null || A.length==0) {
            return 0;
        }

        int n = A[0].length();
        int[] dp  = new int[n];
        Arrays.fill(dp,1);
        int max=0;
        for(int i=1;i<n;i++)  {

            for(int j=0;j<i;j++) {

                if(check(i,j,A)) {
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }

            }

            max = Math.max(max,dp[i]);

        }

        return n-max;

    }

    private boolean check(int i, int j, String[] A) {
        for (String a : A) {
            if (a.charAt(j) > a.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}

/*

For each position i,we track the maximum increasing subsequence.
To do that, we analyze all j < i, and if A[j] < A[i] for all strings , then dp[i] = dp[j] + 1.
The runtime complexity is O(n * n * m), where n is the number of characters, and m is the number of strings.

Unlike LIS,
we cannot use the binary search approach here because there is no stable comparison (e.g. ["ba", "ab"] are both "less" than each other).

 */