/*

Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".



Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
Example 2:

Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
Example 3:

Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4


Note:

0 <= N <= 10^9

 */
package bits.medium;

public class ConvertToBaseNeg2 {
    public String baseNeg2(int n) {
        if (n == 0)  {
            return "0";
        }

        int base = -2;

        StringBuilder ans = new StringBuilder();
        while (n != 0)
        {
            // Remainder by negative base can be negative also
            int remainder = n % base;
            n /= base;

            // if remainder is negative, add Math.abs(base) to it and add 1 to n
            if (remainder < 0)
            {
                remainder += (-base);
                n += 1;
            }

            // convert remainder to string add into the result
            ans.insert(0, remainder);
        }

        return ans.toString();
    }
}

/*

Intuition:
Maybe write a base2 function first?
How about add minus -?
Done.

Explanation:
base2 function is quite basis of basis.
check last digit, shift to right.
base-2 is totally no difference, just add a sign -.


Time Complexity:
O(logN) Time, O(logN) space.
Note that I didn't deal with string concatenation,
and just take this part as O(1).



 */