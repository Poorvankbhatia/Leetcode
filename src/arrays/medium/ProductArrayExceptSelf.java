package arrays.medium;/*

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of
all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of
space complexity analysis.)

 */

import java.util.Arrays;

/**
 * Created by poorvank on 21/08/16.
 */
public class ProductArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        int right = 1;
        int left = 1;

        result[0] = 1;
        for (int i=1;i<n;i++) {
            result[i] = nums[i-1]*left;
            left = result[i];
        }


        for (int i=n-2;i>=0;i--) {
            //Calculating final result
            result[i] *= nums[i+1] * right;
            right = nums[i+1] * right;
        }


        return result;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4};
        ProductArrayExceptSelf ps = new ProductArrayExceptSelf();
        System.out.println(Arrays.toString(ps.productExceptSelf(nums)));

    }

}
