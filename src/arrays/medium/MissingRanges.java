/*

Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 01/06/17.
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<>();
        if(nums==null || nums.length==0) {
            addToList(list,lower,upper);
            return list;
        }

        if(nums[0]>lower) {
            addToList(list,lower,nums[0]-1);
        }

        for(int i=1;i<nums.length;i++) {
            if(nums[i]==nums[i-1]) {
                continue;
            }
            if(nums[i]>=2+nums[i-1]) {
                addToList(list,nums[i-1]+1,nums[i]-1);
            }
        }

        if(nums[nums.length-1]<upper) {
            addToList(list,nums[nums.length-1]+1,upper);
        }

        return list;

    }

    private void addToList(List<String> list,int lower,int upper) {
        if(lower<upper) {
            list.add(lower+"->"+upper);
        } else {
            list.add(upper+"");
        }
    }


    public static void main(String[] args) {
        int[] arr = {0};
        System.out.println(new MissingRanges().findMissingRanges(arr,0,0));
    }

}
