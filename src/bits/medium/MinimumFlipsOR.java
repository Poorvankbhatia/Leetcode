/*

Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.


Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
Example 2:

Input: a = 4, b = 2, c = 7
Output: 1
Example 3:

Input: a = 1, b = 2, c = 3
Output: 0


Constraints:

1 <= a <= 10^9
1 <= b <= 10^9
1 <= c <= 10^9

 */
package bits.medium;

public class MinimumFlipsOR {

    public int minFlips(int a, int b, int c) {
        int ans = 0, ab = a | b, equal = ab ^ c;
        for (int i = 0; i < 31; ++i) {
            int mask = 1 << i;
            if ((equal & mask) > 0)
                ans += (ab & mask) < (c & mask) || (a & mask) != (b & mask) ? 1 : 2;
        }
        return ans;
    }

}

/*

if (a | b) ^ c is 0, a | b and c are equal, otherwise not equal and we need to check them bit by bit;
For ith bit of (a | b) ^ c, use 1 << i as mask to do & operation to check if the bit is 0; if not, ith bits of a | b and c are not same and we need to find which one is 0:
i) the ith bit of a | b less than that of c; ith bit of a | b must be 0, we only need to flip the ith bit of either a or b;
ii) the ith bit of a | b bigger than that of c; ith bit of a | b must be 1, but if only one of a or b's ith bit is 1, we only need to flip one of them;
iii) Other case, we need to flip both set bit of a and b, hence need 2 operations.

 */
