/*

Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].



Example 1:

Input: [1,17,8]
Output: 2
Explanation:
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1


Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9

 */
package backtracking.hard;

import java.util.Arrays;

public class NumberOfSquareFulArrays {
    int res = 0;
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        dfs(A, 0, -1);
        return res;
    }

    private void dfs(int[] A, int cnt, int prev) {
        // already visited all the numbers
        if (cnt == A.length) {
            res++;
            return;
        }

        for (int i = 0; i < A.length; i++) {
            // skip the number that is already visited
            if (A[i] == -1) continue;

            // skip the number that is equal to left one (same with permutation de-duplication)
            if (i > 0 && A[i] == A[i - 1]) continue;

            // skip the number that added to prev is not squareful
            int root = (int)Math.sqrt(A[i] + prev);
            if (prev != -1 && root * root != A[i] + prev) continue;

            int tmp = A[i]; // remember the number
            A[i] = -1;      // then make it as visited (use -1)
            dfs(A, cnt + 1, tmp);
            A[i] = tmp;     // restore the number
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,2,2};
        System.out.println(new NumberOfSquareFulArrays().numSquarefulPerms(arr));
    }
}

/*

The idea is to choose a number each time that:

unvisited (change to -1 as visited)
different with left one
the sum with prev is squareful
Time: O(n!*n)



 */