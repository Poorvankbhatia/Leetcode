/*

Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray
of **size at least 2**
that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

 */
package arrays.medium;

import java.util.HashMap;

/**
 * Created by poorvank on 26/02/17.
 */
public class ContinuousSubArraySum {

    public boolean checkSubarraySum(int[] nums, int k) {

        if (null == nums) {
            return false;
        }
        if (k == 1 && nums.length >= 2) {
            return true;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];

            if (k != 0) {
                sum = sum % k;
                // When we have input like {3,3} and 6
                if (sum == 0 && i >= 1) {
                    return true;
                }
            }

            if (map.containsKey(sum)) {
                if (k != 0) {
                    return true;
                } else {
                    // for input like {0,0,3} and 0
                    if (nums[map.get(sum)] == 0) {
                        return true;
                    }
                }
            }
            map.put(sum, i);

        }

        return false;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 3};
        int k = 6;
        System.out.println(new ContinuousSubArraySum().checkSubarraySum(arr, k));
    }

}

/*

Same concept used as subarray with 0 sum.

 */