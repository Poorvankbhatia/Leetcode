/*

Given a circular array (the next element of the last element is the first element of the array),
print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to
its traversing-order next in the array, which means you could search circularly to find its next greater number.
If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.

 */
package stack.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by poorvank on 09/02/17.
 */
public class NextGreaterElement2 {

    public int[] nextGreaterElements(int[] nums) {

        if(null==nums || nums.length==0) {
            return new int[0];
        }

        int[] result = new int[nums.length];

        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        for (int i=1;i<nums.length;i++) {

            while (!stack.isEmpty() && nums[i]>nums[stack.peek()]) {
                result[stack.peek()] = nums[i];
                stack.pop();
            }

            stack.push(i);

        }

        /*
            Now the stack will only contain the elements which do not have greater elements on the right,
            So we run over the array again to find circularly closest elements
         */

        if(!stack.isEmpty()) {
            //2nd Run from the start of the array on the remaining elements
            for (int num : nums) {
                while (!stack.isEmpty() && num > nums[stack.peek()]) {
                    result[stack.peek()] = num;
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100,1,11,1,120,111,123,1,-1,-100};
        System.out.print(Arrays.toString(new NextGreaterElement2().nextGreaterElements(arr)));
    }

}
