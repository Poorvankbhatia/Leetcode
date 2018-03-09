/*

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanat
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.

 */
package bits;

/**
 * Created by poorvank on 18/12/16.
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {

        int xor = x^y;
        return countSetBits(xor);

    }

    private int countSetBits(int n) {
        int count = 0;
        while (n!=0) {
            count += n&1;
            n >>=1 ;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new HammingDistance().hammingDistance(5,3));
    }

}
 /*

 1011
 0001

  */