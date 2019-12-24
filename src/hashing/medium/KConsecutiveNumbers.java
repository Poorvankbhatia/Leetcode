/*

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length

 */
package hashing.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KConsecutiveNumbers {

    public boolean isPossibleDivide(int[] nums, int k) {

        if(nums.length % k != 0) return false;

        Map<Integer,Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n,map.getOrDefault(n,0)+1);
        }
        Arrays.sort(nums);
        for (int n : nums) {
            if(map.get(n)!=0) {
                for (int x=1;x<k;x++) {
                    if(!map.containsKey(n+x) || (map.containsKey(n+x) && map.get(n+x)==0)) {
                        return false;
                    } else {
                        map.put(n+x,map.getOrDefault(n+x,0)-1);
                    }
                }
                map.put(n,map.get(n)-1);
            }

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new KConsecutiveNumbers().isPossibleDivide(new int[]{1,2,3,3,4,4,5,6},4));
    }

}
