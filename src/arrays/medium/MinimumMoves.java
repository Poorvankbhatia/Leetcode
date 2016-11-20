/*

Given a non-empty integer array, find the minimum number of moves required to
make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

 */

package arrays.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 20/11/16.
 */
public class MinimumMoves {

    public int minMoves2(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);
        int mid = 0;
        if(n%2!=0) {
            mid = nums[n/2];
        } else {
            mid = (nums[n/2]+nums[n/2-1])/2;
        }

        int res = 0;
        for (Integer e : nums) {
            res += Math.abs(mid-e);
        }

        return res;

    }

}

//Make all numbers same as average.
// {1,0,0,8,6} ans 14 average to 1;
