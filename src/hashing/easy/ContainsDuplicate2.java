/*

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that
 nums[i] = nums[j] and the difference between i and j is at most k.



 */
package hashing.easy;

import java.util.HashMap;

/**
 * Created by poorvank on 02/12/16.
 */
public class ContainsDuplicate2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        if(nums.length==0) {
            return false;
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if(map.containsKey(nums[i]) && i-map.get(nums[i])<=k) {
                return true;
            }
            map.put(nums[i],i);
        }

        return false;

    }
}
