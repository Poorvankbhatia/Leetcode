/*
You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.



Example 1:

Input: nums = [4,1,3,3]
Output: 5
Explanation: The pair (0, 1) is a bad pair since 1 - 0 != 1 - 4.
The pair (0, 2) is a bad pair since 2 - 0 != 3 - 4, 2 != -1.
The pair (0, 3) is a bad pair since 3 - 0 != 3 - 4, 3 != -1.
The pair (1, 2) is a bad pair since 2 - 1 != 3 - 1, 1 != 2.
The pair (2, 3) is a bad pair since 3 - 2 != 3 - 3, 1 != 0.
There are a total of 5 bad pairs, so we return 5.
Example 2:

Input: nums = [1,2,3,4,5]
Output: 0
Explanation: There are no bad pairs.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
 */
package hashing.medium;

import java.util.HashMap;
import java.util.Map;

public class BadPairs {

    public long countBadPairs(int[] nums) {

        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int diff = nums[i]-i;
            map.put(diff,map.getOrDefault(diff,0)+1);
            ans += (i+1)-(map.get(diff));
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new BadPairs().countBadPairs(new int[]{4,1,3,3}));
    }

}

/*

j - i != nums[j] - nums[i] meaning
nums[i]-i != nums[j]-j is a bad pair
nums[i]-i = nums[j]-j means a good pair.
We can store all the diffs<->count in a map.
At every index subtract from the total size of the array seen till now , the elements having that common diff from
the map.

 */