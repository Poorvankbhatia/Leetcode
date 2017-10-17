/*

Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.
Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.
Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.

 */
package bits.easy;

/**
 * Created by poorvank.b on 16/10/17.
 */
public class AlternatingBits {

    public boolean hasAlternatingBits(int n) {

        // Right Shifting bit by 1;
        Integer m  = n>>1;
        System.out.println(Integer.toBinaryString(m));
        // Xor with one bit shifted to right
        Integer y = m ^ n;
        System.out.println(Integer.toBinaryString(y));
        // And with next number
        y=y&(y+1);
        System.out.println(Integer.toBinaryString(y));
        return y==0;

    }

    public static void main(String[] args) {
        new AlternatingBits().hasAlternatingBits(10);
    }

}

/*

"Alternating pattern" means a pattern in which no two adjacent bits are the same, i.e. a pattern like 01010101 or 10101010.

The solution has two parts:

Part one combines a number with itself shifted right by one bit
Part two verifies that the result is 2n-1
Part one uses XOR operator ^, which produces a 1 only when the two bits being XOR-ed are different. Since we are XOR-ing
a number with itself shifted left, an alternating pattern would produce all ones; any other pattern would produce at least one zero in the middle.

Part two adds one to the result of the XOR, and checks that the result is 2n by repeated division by two.

 */