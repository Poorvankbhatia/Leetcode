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


        if(null == nums || nums.length==0) {
            return 0;
        } else if(nums.length<3) {
            int sum =0;
            for (Integer n :nums) {
                sum += n;
            }
            return sum;
        }

        Arrays.sort(nums);

        int ans = nums[0] + nums[1] + nums[2];

        for (int i=0;i<nums.length-2;i++) {

            int j = i+1;
            int k = nums.length-1;

            while (j<k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(Math.abs(target-ans)>Math.abs(target-sum)) {
                    ans = sum;
                    if(ans == target) {
                        return target;
                    }
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
