/*

A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range [0, N - 1].

Sets S[K] for 0 <= K < N are defined as follows:

S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.

Sets S[K] are finite for each K and should NOT contain duplicates.

Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for this array.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation:
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of array A is an integer within the range [0, N-1].

 */

package bfsdfs.medium;

/**
 * Created by poorvank on 30/05/17.
 */
public class ArrayNesting {


    public int arrayNesting(int[] nums) {

        int n = nums.length;

        if(n==0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        boolean[] visited = new boolean[n];

        for (int i=0;i<nums.length;i++) {
            /*
             If visited before then the it must be less than the one which is already calculated.
             */
            if(!visited[i]) {
                max = Math.max(dfs(nums,i,visited),max);
            }
        }

        return max;

    }

    private int dfs(int[] nums, int start, boolean[] visited) {
        int i = start, count = 0;
        while (count == 0 || i != start) {
            visited[i] = true;
            i = nums[i];
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,4,0,3,8,6,2,7,5,1};
        System.out.println(new ArrayNesting().arrayNesting(arr));
    }

}

/*
Time complexity : O(n). Every element of the nums array will be considered atmost once.

Without using space:

Instead of making use of a separate array to keep track of the same, we can mark the visited elements in the original array nums itself.
Since, the range of the elements can only be between 1 to 20,000, we can put a very large integer value Integer.MAX_VALUEInteger.
MAX_VALUE at the position which has been visited. The rest process of traversals remains the same as in the last approach.

 */