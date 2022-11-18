/*

Given two integer arrays nums1 and nums2 of length n, count the pairs of indices (i, j)
such that i < j and nums1[i] + nums1[j] > nums2[i] + nums2[j].

Return the number of pairs satisfying the condition.



Example 1:

Input: nums1 = [2,1,2,1], nums2 = [1,2,1,2]
Output: 1
Explanation: The pairs satisfying the condition are:
- (0, 2) where 2 + 2 > 1 + 1.
Example 2:

Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
Output: 5
Explanation: The pairs satisfying the condition are:
- (0, 1) where 1 + 10 > 1 + 4.
- (0, 2) where 1 + 6 > 1 + 1.
- (1, 2) where 10 + 6 > 4 + 1.
- (1, 3) where 10 + 2 > 4 + 5.
- (2, 3) where 6 + 2 > 1 + 5.


Constraints:

n == nums1.length == nums2.length
1 <= n <= 105
1 <= nums1[i], nums2[i] <= 105

 */
package binarysearch.medium;

import java.util.Arrays;

public class CountPairs {

    public long countPairs(int[] nums1, int[] nums2) {
        long res = 0;
        int n = nums1.length;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            int start = helper(nums, i);
            res += start!=-1?(n - start):0;
        }
        return res;
    }

    public int helper(int[] nums, int index) {
        int low = index + 1, high = nums.length-1;
        if(low>high) return -1;
        while (high-low>1) {
            int mid = low + (high - low) / 2;
            if (nums[mid] + nums[index] <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (nums[low] + nums[index])>0?low:(nums[high] + nums[index])>0?high:nums.length;
    }

    public static void main(String[] args) {
        System.out.println(new CountPairs().countPairs(new int[]{2,1,2,1},
                                                       new int[]{1,2,1,2}));
    }

}

/*

Problem can be converted into (nums1[i] - nums2[i]) + (nums1[j] - nums2[j]) > 0.
After getting the diff array, find all the pairs with sum > 0.

 */