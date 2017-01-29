/*

Given a non-empty array containing only positive integers,
find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank on 11/12/16.
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

        int sum =0;
        for (Integer num : nums) {
            sum += num;
        }

        if((sum & 1)!=0) {
            return false;
        }

        sum = sum/2;

        return canPartitionDP(nums,sum,nums.length);

    }


    private boolean canPartitionRecursive(int[] nums,int sum,int n) {

        if(sum<0 || n<=0) {
            return false;
        }

        if(sum==0) {
            return true;
        }

        if(nums[n-1]>sum) {
            return canPartitionRecursive(nums,sum,n-1);
        }

        return canPartitionRecursive(nums,sum-nums[n-1],n-1) || canPartitionRecursive(nums,sum,n-1);

    }


    private boolean canPartitionDP(int[] nums,int sum,int n) {

        //dpTable[i][j] indicates if we can make a sum of i, with j array elements
        boolean[][] dpTable = new boolean[sum+1][n+1];

        //Filling first row, if sum is zero than we can form 2 subsets having no element so marking it true
        for (int j=0;j<=n;j++) {
            dpTable[0][j] = true;
        }

        for (int i=1;i<=sum;i++) {
            dpTable[i][0] = false;
        }

        for (int i=1;i<=sum;i++) {
            for (int j=1;j<=n;j++) {
                //Checking if weight is greater than the current nums element
                if(i>=nums[j-1]) {
                    /*
                     1st term represents excluding the current element,
                     2nd term is including the current element
                     */
                    dpTable[i][j] = dpTable[i][j-1] || dpTable[i-nums[j-1]][j-1];
                } else {
                    dpTable[i][j] = dpTable[i][j-1];
                }
            }
        }

        return dpTable[sum][n];


    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,6};
        System.out.println(new PartitionEqualSubsetSum().canPartition(nums));
    }

}

/*

Time Complexity: O(sum*n)
Auxiliary Space: O(sum*n)

Without using space
 boolean[] dp = new boolean[sum + 1];
        // dp init
        dp[0] = true;
        // dp transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= nums[i-1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i-1]];
            }
        }
        return dp[sum];


 */