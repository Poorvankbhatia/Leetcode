package dyanamicprogramming.medium;

/**
 * Created by poorvank on 11/09/16.
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int cumulativeSum = 0;

        for (int num : nums) {

            cumulativeSum += num;

            if (maxSum < cumulativeSum) {
                maxSum = cumulativeSum;
            }
            if (cumulativeSum < 0) {
                cumulativeSum = 0;
            }

        }

        return maxSum;

    }

    public static void main(String[] args) {

        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaximumSubarray().maxSubArray(nums));

    }

}
