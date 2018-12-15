/*

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 20/01/17.
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {


        int n = nums.length;
        int val = Integer.MAX_VALUE;
        int ans=0;
        Arrays.sort(nums);

        for(int i=0;i<n-2;i++) {
            int j=i+1;
            int k=n-1;

            while(j<k) {
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==target) {
                    return target;
                }
                if(Math.abs(sum-target)<val) {
                    ans = sum;
                    val = Math.abs(sum-target);
                }
                if(sum>target) {
                    k--;
                } else {
                   j++;
                }
            }

        }

        return ans;


    }


    public static void main(String[] args) {
        int[] arr = new int[]{-1,2,1,-4};
        int  target = 1;
        System.out.println(new ThreeSumClosest().threeSumClosest(arr,target));
    }
}
