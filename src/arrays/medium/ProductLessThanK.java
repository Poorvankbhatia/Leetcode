/*
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.
 */
package arrays.medium;

/**
 * Created by poorvank.b on 22/10/17.
 */
public class ProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        if(nums==null || nums.length==0 || k==0) {
            return 0;
        }

        int result=0;

        for (int i=0;i<nums.length;i++) {
            int prod = nums[i];
            if(prod<k) {
                result++;
                for (int j=i+1;j<nums.length;j++) {
                    prod*=nums[j];
                    if (prod < k) {
                        result++;
                    } else {
                        break;
                    }
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        System.out.println(new ProductLessThanK().numSubarrayProductLessThanK(arr,10));
    }

}
