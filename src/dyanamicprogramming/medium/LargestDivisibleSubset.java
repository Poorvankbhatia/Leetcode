/*

Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj)
of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

 */
package dyanamicprogramming.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 15/05/17.
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {


        List<List<Integer>> result = new ArrayList<>();

        if(nums.length==0) {
            return new ArrayList<>();
        }

        // In case only one element is present, we should return it.
        if(nums.length==1) {
            List<Integer> list  = new ArrayList<>();
            list.add(nums[0]);
            return list;
        }

        // Sorting is done for cases like {1,9,27,3}, so that 3 is included in final result
        Arrays.sort(nums);

        // Same as LIS
        int n = nums.length;

        int[] count = new int[n];
        for (int i=0;i<n;i++) {
            count[i]=1;
            result.add(i,new ArrayList<>());
        }

        int len = 1;
        for (int i=1;i<n;i++) {
            for (int j=0;j<i;j++) {
                if((nums[i]%nums[j]==0) && count[j]+1>count[i]) {
                    count[i] = count[j]+1;
                    result.get(i).add(nums[j]);
                    if(len<count[i]) {
                        len = count[i];
                    }
                }
            }
            result.get(i).add(nums[i]);
        }


        for (List<Integer> list : result) {
            if(list.size()==len) {
                return list;
            }
        }

        return null;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,9,27,3,1};
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(nums));
    }

}
