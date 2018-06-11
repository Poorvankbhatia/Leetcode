/*

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n
that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?

 */
package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 08/06/18.
 */
public class ThreeSumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int tp = nums[i];
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] >= target - tp) {
                    hi--;
                } else {
                    res += hi - lo;
                    lo++;
                }
            }
        }
        return res;
    }

}

/**
 *  G I
 */
