/*

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

 */
package arrays.medium;

/**
 * Created by poorvank.b on 07/02/18.
 */
public class IncreasingTriplet {

    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) {
            return false;
        }

        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;

        for (Integer n : nums) {
            if(n<=start) {
                start=n;
            } else if(n<=end) {
                end = n;
            } else {
                return true;
            }
        }

        return false;
    }

}
