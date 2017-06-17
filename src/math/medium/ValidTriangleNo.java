/*

Given an array consists of non-negative integers, your task is to count the number of triplets chosen
from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are:
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

 */
package math.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 11/06/17.
 */
public class ValidTriangleNo {

    public int triangleNumber(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);

        int count = 0;

        for (int i = 0; i < n-2; ++i)
        {

            if(nums[i]==0) {
                continue;
            }

            int k = i + 2;

            for (int j = i+1; j < n; ++j)
            {

                while (k < n && nums[i] + nums[j] > nums[k])
                    ++k;

                /* Total number of possible triangles that can be
                  formed with the two fixed elements is k - j - 1.
                  The two fixed elements are nums[i] and nums[j].  All
                  elements between nums[j+1] to nums[k-1] can form a
                  triangle with nums[i] and nums[j]. One is subtracted
                  from k because k is incremented one extra in above
                  while loop. k will always be greater than j. If j
                  becomes equal to k, then above loop will increment
                  k, because nums[k] + nums[i] is always/ greater than
                  nums[k] */
                count += k - j - 1;
            }
        }
        return count<0?0:count;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,2,0,2,0,0,3,4};
        System.out.println(new ValidTriangleNo().triangleNumber(nums));
    }

}

/*

Let a, b and c be three sides. The below condition must hold for a triangle (Sum of two sides is greater than the third side)
i) a + b > c
ii) b + c > a
iii) a + c > b

Following are steps to count triangle.

1. Sort the array in non-decreasing order.

2. Initialize two pointers ‘i’ and ‘j’ to first and second elements respectively, and initialize count of triangles as 0.

3. Fix ‘i’ and ‘j’ and find the rightmost index ‘k’ (or largest ‘nums[k]’) such that ‘nums[i] + nums[j] > nums[k]’. The number of triangles
that can be formed with ‘nums[i]’ and ‘nums[j]’ as two sides is ‘k – j’. Add ‘k – j’ to count of triangles.

Let us consider ‘nums[i]’ as ‘a’, ‘nums[j]’ as b and all elements between ‘nums[j+1]’ and ‘nums[k]’ as ‘c’. The above mentioned conditions (ii)
and (iii) are satisfied because ‘nums[i] < nums[j] < nums[k]'. And we check for condition (i) when we pick 'k' 4. Increment ‘j’ to fix the second
 element again.

Note that in step 3, we can use the previous value of ‘k’. The reason is simple, if we know that the value of ‘nums[i] + nums[j-1]’ is greater
than ‘nums[k]’, then we can say ‘nums[i] + nums[j]’ will also be greater than ‘nums[k]’, because the array is sorted in increasing order.

5. If ‘j’ has reached end, then increment ‘i’. Initialize ‘j’ as ‘i + 1’, ‘k’ as ‘i+2’ and repeat the steps 3 and 4.

 */