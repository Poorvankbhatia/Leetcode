/*

Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number
(from most-significant-bit to least-significant-bit.)

Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.

Example 1:

Input: [0,1,1]
Output: [true,false,false]
Explanation:
The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.
Example 2:

Input: [1,1,1]
Output: [false,false,false]
Example 3:

Input: [0,1,1,1,1,1]
Output: [true,false,false,false,true,false]
Example 4:

Input: [1,1,1,0,1]
Output: [false,false,false,false,false]


Note:

1 <= A.length <= 30000
A[i] is 0 or 1

 */
package bits.medium;

import java.util.ArrayList;
import java.util.List;

public class PrefixDivisibleBy5 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int remainder = 0;
        for(int bit : A) {
            if (bit == 1)
                remainder = (remainder * 2 + 1) % 5;
            if (bit == 0)
                remainder = (remainder * 2) % 5;
            if(remainder%5==0) {
                list.add(true);
            } else {
                list.add(false);
            }
        }
        return list;
    }
}

/*

 we need to observe how the value of a binary number changes, when it is appended by 0 or 1.

Let’s take an example. Suppose you have binary number 1.
If it is appended by 0 it will become 10 (2 in decimal) means 2 times of the previous value.
If it is appended by 1 it will become 11(3 in decimal), 2 times of previous value +1.

How does it help in getting the remainder?
Any number (n) can be written in the form m = an + r where a, n and r are integers and r is the remainder.
So when m is multiplied by any number so the remainder. Suppose m is multiplied by x so m will be mx = xan + xr. so (mx)%n = (xan)%n + (xr)%n = 0 + (xr)%n = (xr)%n;

We need to just do the above calculation (calculation of value of number when it is appended by 0 or 1 ) only over remainder.

When a binary number is appended by 0 (means
multiplied by 2), the new remainder can be
calculated based on current remainder only.
r = 2*r % n;

And when a binary number is appended by 1.
r = (2*r + 1) % n;

 */