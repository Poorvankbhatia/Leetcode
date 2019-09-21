/*

Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most
one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that
there is still at least one element left and the sum of the remaining elements is maximum possible.

Note that the subarray needs to be non-empty after deleting one element.



Example 1:

Input: arr = [1,-2,0,3]
Output: 4
Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
Example 2:

Input: arr = [1,-2,-2,3]
Output: 3
Explanation: We just choose [3] and it's the maximum sum.
Example 3:

Input: arr = [-1,-1,-1,-1]
Output: -1
Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.


Constraints:

1 <= arr.length <= 10^5
-10^4 <= arr[i] <= 10^4


 */
package dyanamicprogramming.medium;

public class MaximumSubArrayWithOneDeletion {

    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] next = new int[n];
        int[] last = new int[n];
        next[0]=arr[0];
        int current = next[0];
        int maxSoFar = arr[0];
        for(int i=1;i<n;i++) {
            current = Math.max(arr[i],current+arr[i]);
            maxSoFar = Math.max(current,maxSoFar);
            next[i] = current;
        }
        //System.out.println(Arrays.toString(next));
        current = maxSoFar = last[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--) {
            current = Math.max(arr[i],current+arr[i]);
            maxSoFar = Math.max(current,maxSoFar);
            last[i] = current;
        }
        //System.out.println(Arrays.toString(last));
        int ans=maxSoFar;
        for(int i=1;i<n-1;i++) {
            ans = Math.max((last[i+1]+next[i-1]),ans);
        }
        return ans;
    }

}

/*

This condition can be handled using two arrays, forward and backward array,
these arrays store the current maximum subarray sum from starting to ith index, and from ith index to ending respectively.

maximum subarray sum after ignoring i’th element will be last[i-1] + next[i+1]
so we loop for all possible i values and we choose maximum among them.

 */