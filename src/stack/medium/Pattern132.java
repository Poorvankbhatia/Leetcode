/*

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such
that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks
whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

 */

package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank on 13/11/16.
 */
public class Pattern132 {

    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int last = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i--) {
            if(nums[i]<last) {
                return true;
            } else {
                while(!stack.isEmpty() && nums[i]>stack.peek()) {
                    last = stack.pop();
                }
            }
            stack.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0,1,1,1,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,0,1,1,1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,0,1,0,1,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,0,1,1,0,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,1,0,1,0,0,0,0,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,0,0,1,0,1,1,1,1,0,0,1,0,0,0,0,1,0,1,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,1,0,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,1,0,0,1,0,0,0,0,1,1,1,1,0,1,0,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,0,0,1,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1,1,0,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,1,0,1,1,1,0};
        //int[] a = new int[]{-1,3,2,0};
        //int[] a = new int[]{-1,2,3,4};
        System.out.println(new Pattern132().find132pattern(a));
    }

}

/*

ALGORITHM: We can start from either side but I think starting from the right allow us to finish in a single pass.
The idea is to start from end and search for valid (s2,s3) pairs, we just need to remember the largest valid s3 value, using a stack will
be effective for this purpose. A number becomes a candidate for s3 if there is any number on the left bigger than it.

CORRECTNESS: As we scan from right to left, we can easily keep track of the largest s3 value of all (s2,s3) candidates encountered so far.
Hence, each time we compare nums[i] with the largest candidate for s3 within the interval nums[i+1]...nums[n-1] we are effectively asking the
question: Is there any 132 sequence with s1 = nums[i]? Therefore, if the function returns false, there must be no 132 sequence.

IMPLEMENTATION:

Have a stack, each time we store a new number, we first pop out all numbers that are smaller than that number. The numbers that are popped out becomes candidate for s3.
We keep track of the maximum of such s3 (which is always the most recently popped number from the stack).
Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3 implies s1 < s2.
RUNTIME: Each item is pushed and popped once at most, the time complexity is therefore O(n).

EXAMPLE:
i = 6, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = None, Stack = Empty
i = 5, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 7, S3 candidate = None, Stack = [9]
i = 4, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 10, S3 candidate = None, Stack = [9,7]
i = 3, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = 9, Stack = [10]
i = 2, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 8, S3 candidate = 9, Stack = [10,9] We have 8<9, sequence (8,10,9) found!

O(n^2)

public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length < 3) {
            return false;
        }

        for(int i = 0; i < nums.length - 2; i++) {
            int bigger = nums[i];
            for(int j = i + 1; j < nums.length; j++) {
                // 1. We don't care about numbers
                // less than a[i]
                if(nums[j] <= nums[i]) continue;

                // 2. If num is greater than bigger
                // then update bigger
                if(nums[j] >= bigger) {
                    bigger = nums[j];
                } else {
                    // Now this number is greater than nums[i]
                    // see 1. and less than bigger, see 2.
                    return true;
                }
            }
        }
        return false;
    }

 */
