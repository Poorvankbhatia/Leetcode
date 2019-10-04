/*

Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

The leftmost element of the subarray is not larger than other elements in the subarray.



Example 1:

Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
Example 2:

Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].
Example 3:

Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].


Note:

1 <= A.length <= 50000
0 <= A[i] <= 100000

 */
package stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfValidSubArrays {

    public int validSubarrays(int[] nums) {
        int res = nums.length;
        if(nums.length <= 1) {
            return res;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(nums[0]);
        for(int i = 1; i < nums.length; i++) {
            int num = nums[i];
            while(!stack.isEmpty() && num < stack.peek()) {
                stack.pop();
            }
            res += stack.size();
            stack.push(num);
        }
        return res;
    }

}

/*

First
brute force solution with O(n^2) solution

example : [1, 4, 2, 5, 3]
for each number in array, we find maximum continuous length
idx = 0, [1, 4, 2, 5, 3] => max length = 5
idx = 1, [4] => max length = 1
idx = 2, [2, 5, 3] => max length = 3
idx = 3, [5] => max length = 1
idx = 4, [3] => max length = 1
res = array.length + (5 -1 ) + (1 - 1) + (3 - 1) + (1 - 1) + (1 - 1) = 11
Now, why don't we keep acending order in stack ?

[1, 4, 2, 5, 3]
count += array.length
idx = 0    stack [1]
idx = 1    (4 >= 1) stack [1, 4]   , count += 1 ([1, 4] is valid)
idx = 2    (2 < 4 ) stack [1, 2] , count += 1 ([1, 4, 2] is valid)
idx = 3    (5 > 2)  stack [1, 2, 5] , count += 2 ([1, 4, 2, 5] and [2, 5] are valid)
idx = 4    (3 < 5)  stack [1, 2, 3] , count += 2 ([1, 4, 2, 5, 3] and [2, 5, 3] are valid)


 */