/*

Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

 */

package arrays.easy;

/**
 * Created by poorvank on 29/01/17.
 */
public class MaximumConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {

        int result = 0,max=0;

        for (int num : nums) {
            if (num == 1) {
                max++;
            } else {
                result = Math.max(result, max);
                max = 0;
            }
        }

        return Math.max(result, max);

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,0,1,1,1};
        System.out.println(new MaximumConsecutiveOnes().findMaxConsecutiveOnes(arr));
    }

}
