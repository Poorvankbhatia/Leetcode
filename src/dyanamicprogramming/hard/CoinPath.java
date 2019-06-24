/*

Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B.
The integer B denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the
array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins.
If Ai is -1, it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins.
You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:
Input: [1,2,4,-1,2], 2
Output: [1,3,5]
Example 2:
Input: [1,2,4,-1,2], 1
Output: []
Note:
Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if
at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
Length of A is in the range of [1, 1000].
B is in the range of [1, 100].


 */
package dyanamicprogramming.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank.b on 11/01/19.
 */
public class CoinPath {

    public List <Integer> cheapestJump(int[] A, int B) {
        int[] next = new int[A.length];
        long[] dp = new long[A.length];
        Arrays.fill(next, -1);
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 2; i >= 0; i--) {
            long minCost = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + B && j < A.length; j++) {
                if (A[j] >= 0) {
                    long cost = A[i] + dp[j];
                    if (cost < minCost) {
                        minCost = cost;
                        next[i] = j;
                    }
                }
            }
            dp[i] = minCost;
        }
        int i;
        for (i = 0; i < A.length && next[i] > 0; i = next[i]) {
            res.add(i + 1);
        }
        if (i == A.length - 1 && A[i] >= 0) {
            res.add(A.length);
        } else {
            return new ArrayList<>();
        }
        return res;
    }

}

/*

We can observe that the cost of jumping till the end of the array starting from the index is only
dependent on the elements following the index and not the ones before it. This inspires us to make use of Dynamic Programming to
solve the current problem.

We again make use of a array to store the next jump locations. We also make use of a with the same size as that of the given array.
is used to store the minimum cost of jumping till the end of the array , starting from the index . We start with the last index as
the current index and proceed backwards for filling the and array.

With as the current index, we consider all the next possible positions from , ,..., , and determine the position, , which leads to a
minimum cost of reaching the end of , which is given by . We update with this corresponding index. We also update with the minimum cost,
to be used by the previous indices' cost calculations.

At the end, we again jump over the indices as per the array and put these indices in the array to be returned.

 */