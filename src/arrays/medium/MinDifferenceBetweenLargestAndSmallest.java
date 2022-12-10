/*

You are given an integer array nums.

In one move, you can choose one element of nums and change it to any value.

Return the minimum difference between the largest and smallest value of nums after performing at most three moves.



Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 2 to 3. nums becomes [5,3,3,4].
In the second move, change 4 to 3. nums becomes [5,3,3,3].
In the third move, change 5 to 3. nums becomes [3,3,3,3].
After performing 3 moves, the difference between the minimum and maximum is 3 - 3 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: We can make at most 3 moves.
In the first move, change 5 to 0. nums becomes [1,0,0,10,14].
In the second move, change 10 to 0. nums becomes [1,0,0,0,14].
In the third move, change 14 to 1. nums becomes [1,0,0,0,1].
After performing 3 moves, the difference between the minimum and maximum is 1 - 0 = 0.
It can be shown that there is no way to make the difference 0 in 3 moves.
Example 3:

Input: nums = [3,100,20]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 100 to 7. nums becomes [4,7,20].
In the second move, change 20 to 7. nums becomes [4,7,7].
In the third move, change 4 to 3. nums becomes [7,7,7].
After performing 3 moves, the difference between the minimum and maximum is 7 - 7 = 0.


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109

 */
package arrays.medium;

import java.util.Arrays;

public class MinDifferenceBetweenLargestAndSmallest {

    public int minDifference(int[] nums) {
        int n = nums.length;
        if(n <= 3) return 0;
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for(int i=0;i<=3;i++)
            res = Math.min(res,nums[n-4+i]-nums[i]);
        return res;
    }

}

/*
Let say arr :
[2,3,1,9,8,6,4]

After sort :
[1,2,3,4,6,8,9]

we'll replace 3 max/min elements with the min/max respectively
obviously to make the diff smaller.

case1 - [1,2,3,4,changed,changed,changed] => res = Min(res,4-1) = 3
case2 - [changed,2,3,4,5,changed,changed] => res = Min(res,5-2) = 3
case3 - [changed,changed,3,4,5,6,changed] => res = Min(res,6-3) = 3
case4 - [changed,changed,changed,4,5,6,7] => res = Min(res,7-4) = 3


 */