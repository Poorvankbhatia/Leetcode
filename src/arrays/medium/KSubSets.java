/*

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

 */
package arrays.medium;

/**
 * Created by poorvank.b on 15/10/17.
 */
public class KSubSets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;

        for (int num : nums)
            sum += num;
        return !(k <= 0 || sum % k != 0) && canPartition(nums, new boolean[nums.length], 0, k, 0, sum / k);

    }

    private boolean canPartition(int[] nums, boolean[] visited, int start_index, int k, int currentSum, int target){
        if(k==0)
            return true;

        if(currentSum == target)
            return canPartition(nums, visited, 0, k-1, 0, target);

        for(int i = start_index; i<nums.length; i++){
            if(!visited[i]){
                visited[i] = true;

                if(canPartition(nums, visited, i+1, k, currentSum + nums[i], target))
                    return true;

                visited[i] = false;
            }
        }
        return false;
    }

}
