/*

The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation,
you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.



Example 1:

Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
4 has a frequency of 3.
Example 2:

Input: nums = [1,4,8,13], k = 5
Output: 2
Explanation: There are multiple optimal solutions:
- Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
- Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
- Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
Example 3:

Input: nums = [3,9,6], k = 2
Output: 1


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 105

 */
package arrays.medium;

import java.util.Arrays;

public class FrequesncyOfTheMostFrequest {

    public int maxFrequency(int[] a, int k) {
        int n = a.length;

        Arrays.sort(a);
        int ans = 0;
        int left = 0;
        long sum = 0;

        for(int right=0; right<n; right++) {
            sum += a[right];

            while((long) a[right] * (right - left + 1) -sum>k) {
                sum -= a[left];
                left++;
            }

            ans = Math.max(ans, right-left+1);
        }

        return ans;
    }
}

/*

O(n * logn)

Here we see that in order to calculate the maximum frequency of a number,
we can increment some numbers less than it. Lets say we increment k numbers less than
x to make them all x. Then operations required will be equal to

Ops = k * x-sum

n1 n2,n3...x..K
k-n1 + k-n2 + k-n3...x terms
kx - sum(n1+n2+n3..x terms)

where sum is the sum of all the numbers less than x which were incremented.

We can utilize this property to create a sliding window approach. So, we first sort the array.
Lets say we have a window (i, j), then that window can be made to have all elements equal
to the maximum (which is a[j]). So, we check if

a[j] * (j-i+1) - sum[i:j] <=k

If it, then we can increase j, else we need to increase i.

 */