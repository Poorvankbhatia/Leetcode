/*

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers
for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.

 */

package stack.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by poorvank on 08/02/17.
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] findNums, int[] nums) {



        if(nums==null || findNums==null || nums.length==0) {
            return new int[0];
        }

        int[] result = new int[findNums.length];

        Stack<Integer> stack = new Stack<>();

        stack.push(0);
        Map<Integer,Integer> map = new HashMap<>();

        for (int i=1;i<nums.length;i++) {

            while (!stack.isEmpty() && nums[i]>nums[stack.peek()]) {
                map.put(nums[stack.peek()],nums[i]);
                stack.pop();
            }

            stack.push(i);

        }



        for (int i=0;i<result.length;i++) {
            result[i] = map.getOrDefault(findNums[i],-1);
        }

        return result;

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] findNums = {2,4};
        System.out.print(Arrays.toString(new NextGreaterElement().nextGreaterElement(findNums,nums)));
    }

}
