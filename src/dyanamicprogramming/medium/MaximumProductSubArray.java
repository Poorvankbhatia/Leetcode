/*

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 07/04/17.
 */
public class MaximumProductSubArray {

    public int maxProduct(int[] nums) {

        if(nums.length==0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i=1;i<nums.length;i++) {
            int num = nums[i];
            if (num >= 0) {
                maxEndingHere = Math.max(maxEndingHere * num,num);
                minEndingHere = Math.min(minEndingHere * num, num);
            }
            else {
                int temp = maxEndingHere;
                maxEndingHere = Math.max(num, minEndingHere * num);
                minEndingHere = Math.min(temp * num,num);
            }

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
            }

        }

        return maxSoFar;

    }

    public static void main(String[] args) {
        int arr[] = {-1,-2,3,-4};
        System.out.println(new MaximumProductSubArray().maxProduct(arr));
    }

}
