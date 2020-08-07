/*
Given an integer n and an integer start.

Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.

Return the bitwise XOR of all elements of nums.



Example 1:

Input: n = 5, start = 0
Output: 8
Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
Where "^" corresponds to bitwise XOR operator.
Example 2:

Input: n = 4, start = 3
Output: 8
Explanation: Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.
Example 3:

Input: n = 1, start = 7
Output: 7
Example 4:

Input: n = 10, start = 5
Output: 2


Constraints:

1 <= n <= 1000
0 <= start <= 1000
n == nums.length
 */
package bits.easy;

public class XOROperationArray {

    public int xorOperation(int n, int start) {
        int res = start;
        for(int i=1;i<n;i++) {
            res ^= (start+2*i);
        }
        return res;
    }

}

/*
O(1) :
We have sequence start, start+2, ..., start+2*(n-1). Last bit in all numbers is the same.
If it is 0 or n is even then last bit of the result is 0 too, otherwise it is 1.
We shift all numbers by 1 to the right and get the new sequence (start/2), (start/2)+1, ..., (start/2)+(n-1).
All we have to do is to calculate xor of all these numbers and append the last bit.

So we have to calculate xor of the sequence (for new start value that equals old start devided by 2)
start, start+1, ..., start+(n-1). It is obvious that (2a)^(2a+1) = 1.
Thus it is very handy to make start even. If start is even we have nothing to do,
but if it is odd we calculate (start-1) ^ (start-1)^start^(start+1)^...^(start+(n-1)).

Now we have to calculate xor of the sequence (for some new start value) start, start+1, ..., start+(n-1)
and start is even. Every consecutive pair gives 1 and we have n/2 pairs. All (n/2) pairs contribute 1
to the result and we have to take into account the last unpaired number if it exists.

class Solution
{
    int xorOperationB(int n, int start)
    {
        if (n % 2 == 0)
            return (n / 2) & 1;
        else
            return ((n / 2) & 1) ^ (start + n - 1);
    }
    int xorOperationA(int n, int start)
    {
        if (start & 1)
            return (start - 1) ^ xorOperationB(n + 1, start - 1);
        else
            return xorOperationB(n, start);
    }
public:
    int xorOperation(int n, int start)
    {
        int ret = 2 * xorOperationA(n, start / 2);
        if (n & start & 1) ret++;
        return ret;
    }
};
 */
