/*

You are given an integer array nums and two integers indexDiff and valueDiff.

Find a pair of indices (i, j) such that:

i != j,
abs(i - j) <= indexDiff.
abs(nums[i] - nums[j]) <= valueDiff, and
Return true if such pair exists or false otherwise.



Example 1:

Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0
Output: true
Explanation: We can choose (i, j) = (0, 3).
We satisfy the three conditions:
i != j --> 0 != 3
abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
Example 2:

Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3
Output: false
Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return false.


Constraints:

2 <= nums.length <= 105
-109 <= nums[i] <= 109
1 <= indexDiff <= nums.length
0 <= valueDiff <= 109

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
            if (set.floor(val) != null && (val - set.floor(val)) <= t)
                return true;
			/*
			 * Returns the least element in this set greater than or equal to
			 * the given element, or null if there is no such element. Specified
			 * by: ceiling(...) in NavigableSet Parameters: e the value to match
			 * Returns: the least element greater than or equal to e, or null if
			 * there is no such element
			 */
            if (set.ceiling(val) != null && (set.ceiling(val) - val) <= t)
                return true;
            set.add(val);
            if (i >= k)
                set.remove(nums[i - k]);
        }
        return false;

    }

}


/*

Let us consider an example where each element is a person's birthday. Your birthday, say some day in March,
is the new element x. Suppose that each month has 30 days and you want to know if anyone has a birthday within t = 30
 days of yours. Immediately, we can rule out all other months except February, March, April.

The reason we know this is because each birthday belongs to a bucket we called month!
And the range covered by the buckets are the same as distance tt which simplifies things a lot.
Any two elements that are not in the same or adjacent buckets must have a distance greater than tt.

We apply the above bucketing principle and design buckets covering the ranges of
..., [0,t], [t+1, 2t+1], ......,[0,t],[t+1,2t+1],.... We keep the window using this buckets.
Then, each time, all we need to check is the bucket that xx belongs to and its two adjacent buckets.
Thus, we have a constant time algorithm for searching almost duplicate in the window.

One thing worth mentioning is the difference from bucket sort – Each of our buckets contains at most one element at
any time, because two elements in a bucket means "almost duplicate" and we can return early from the function.
Therefore, a HashMap with an element associated with a bucket label is enough for our purpose.



public class Solution {
    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x / w) - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t+1; // t==0 case.
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
}


w = 3

i  i/w* desired**
-5 -1   -2
-4 -1   -2
-3 -1   -1
-2  0   -1
-1  0   -1
 0  0    0
 1  0    0
 2  0    0
 3  1    1
 4  1    1
 5  1    1

*  java   - rounds towards 0

For the negative case the, - 1, ensures negative values with magnitude less then t dont
map to zero where positive ones with same magnitude will also map to zero.

So the above shows:
(i)  Why we need the `i < 0 ? (i + 1) / w - 1 : i / w;` for java

The way I got (i + 1) / w - 1 was:
(i)   Notice that I get the desired if I map to a number above me:
      -1 -> -3, -2 -> -4, -3 -> -5
(ii)  To get there, I have to shift myself upward by (w - 1), so:
      i - (w - 1)
(iii) Now i/w becomes (i - (w - 1)) / w == (i + 1) / w - 1

 */