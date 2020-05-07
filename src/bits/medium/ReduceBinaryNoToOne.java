/*
Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:

If the current number is even, you have to divide it by 2.

If the current number is odd, you have to add 1 to it.

It's guaranteed that you can always reach to one for all testcases.



Example 1:

Input: s = "1101"
Output: 6
Explanation: "1101" corressponds to number 13 in their decimal representation.
Step 1) 13 is odd, add 1 and obtain 14.
Step 2) 14 is even, divide by 2 and obtain 7.
Step 3) 7 is odd, add 1 and obtain 8.
Step 4) 8 is even, divide by 2 and obtain 4.
Step 5) 4 is even, divide by 2 and obtain 2.
Step 6) 2 is even, divide by 2 and obtain 1.
Example 2:

Input: s = "10"
Output: 1
Explanation: "10" corressponds to number 2 in their decimal representation.
Step 1) 2 is even, divide by 2 and obtain 1.
Example 3:

Input: s = "1"
Output: 0


Constraints:

1 <= s.length <= 500
s consists of characters '0' or '1'
s[0] == '1'
 */
package bits.medium;

public class ReduceBinaryNoToOne {

    public int numSteps(String s) {

        int c=0;
        while (!isOne(s)) {
            if(isDivisibleBy2(s)) {
                s = rightShiftByOne(s);
            } else {
                s = addOne(s);
            }
            c++;
        }
        return c;
    }

    //In the binary string, check for last k bits.
    // If the all the last k bits are 0, then the binary number is evenly divisible by 2k else it is not evenly divisible
    private boolean isDivisibleBy2(String s) {
        return s.charAt(s.length()-1)=='0';
    }

    // Check if all positions are zero except last.
    private boolean isOne(String s) {
        for (int i=s.length()-1;i>=0;i--) {
            if((i==s.length()-1 && s.charAt(i)=='0') || (i!=s.length()-1 && s.charAt(i)=='1')) {
                return false;
            }
        }
        return true;
    }

    // Right shift by 1 to divide.
    private String rightShiftByOne(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0").append(s, 0, s.length()-1);
        return stringBuilder.toString();
    }
    //Simple addition.
    private String addOne(String s) {
        StringBuilder sb = new StringBuilder();
        int i=s.length()-1;
        while (i>=0) {
            if(s.charAt(i)=='1') {
                sb.append(0);
            } else {
                sb.append(1);
                break;
            }
            i--;
        }
        return i==-1?1+sb.toString():s.substring(0,i)+sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReduceBinaryNoToOne().numSteps("11"));
    }


}

/*

Better Method:

 division by two is the same as the right shift by one bit (character).
 If the bit is 0, we just do the shift - one operation.
 If the bit is 1 - we do plus one, and our bit changes to zero. So, we set carry to 1 and shift. Two operations.

 We have three phases here:

We haven't encountered any 1. Every char adds one operation.
We encounter our first 1. We set carry to 1 and add two operations.
The rest:
3A. Every 1 needs one operation (carry makes it 0). carry is still 1 due to addition.
3B. Every 0 needs two operations (carry makes it 1). carry is still 1 as we need to add 1 in this case.
Observation: as you can see from 3A and 3B, carry is always 1 after the second phase.

    int res = 0, carry = 0;
    for (int i = s.length() - 1; i > 0; --i) {
        ++res;
        if (s.charAt(i) - '0' + carry == 1) {
            carry = 1;
            ++res;
        }
    }
    return res + carry;

 */