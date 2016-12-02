/*

Given an array of integers, find out whether there are two distinct indices i and j in the array
 such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

 */
package hashing.medium;

import java.util.TreeSet;

/**
 * Created by poorvank on 02/12/16.
 */
public class ContainsDuplicate3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (nums.length <= 0 || k <= 0) {
            return false;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
			/*
			 * Returns the greatest element in this set less than or equal to
			 * the given element, or null if there is no such element. Specified
			 * by: floor(...) in NavigableSet Parameters: e the value to match
			 * Returns: the greatest element less than or equal to e, or null if
			 * there is no such element
			 */
            if (set.floor(val) != null && (set.floor(val) + t) >= val)
                return true;
			/*
			 * Returns the least element in this set greater than or equal to
			 * the given element, or null if there is no such element. Specified
			 * by: ceiling(...) in NavigableSet Parameters: e the value to match
			 * Returns: the least element greater than or equal to e, or null if
			 * there is no such element
			 */
            if (set.ceiling(val) != null && (set.ceiling(val) - t) <= val)
                return true;
            set.add(val);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;

    }

}


/*

Could'nt solve , solution from one of the comments

 */