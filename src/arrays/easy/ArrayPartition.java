/*

Given an array of 2n integers, your task is to group these integers into n pairs of integer,
say (a1, b2), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4.

 */

package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank.b on 23/04/17.
 */
public class ArrayPartition {

    public int arrayPairSum(int[] nums) {

        if(nums.length==0) {
            return 0;
        }

        Arrays.sort(nums);

        int sum =0;

        for (int i=0;i<nums.length;i+=2) {
            sum+= nums[i];
        }

        return sum;
    }

}

/*

Minimum sum for the above example will be when we group: (1,3) and (2,4)

Hence we first sort the array and get sum of alternate elements, because these will be paired to next higher element after sorting,
and do a sum.

 */