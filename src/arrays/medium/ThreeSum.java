/*

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */
package arrays.medium;

import java.util.*;

/**
 * Created by poorvank on 21/01/17.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if(null == nums || nums.length==0) {
            return result;
        }

        Arrays.sort(nums);

        int j,k,sum;

        for(int i=0;i<nums.length-2;i++){
            j = i+1;
            k = nums.length - 1;
            while(j<k){
                sum = nums[i] + nums[j] + nums[k];
                if (sum == 0){
                    List<Integer> ls = new ArrayList<Integer>();
                    ls.add(nums[i]);
                    ls.add(nums[j]);
                    ls.add(nums[k]);
                    result.add(ls);
                    j++;k--;
                    while(j<k && nums[k]==nums[k+1]) k--;//to avoid duplicates
                    while(j<k && nums[j]==nums[j-1]) j++;//to avoid duplicates
                } else if (sum > 0){
                    k--;
                    while(j<k && nums[k]==nums[k+1]) k--;//optional skip for non-zero triplets
                } else {//
                    j++;
                    while(j<k && nums[j]==nums[j-1]) j++;//optional skip for non-zero triplets
                }
            }
            //Consider case -1,-1,2
            while(i<nums.length-2 && nums[i]==nums[i+1]){ //to avoid duplicates
                i++;
            }
        }


        return result;


    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,0,0};
        System.out.println(new ThreeSum().threeSum(arr));
    }

}
