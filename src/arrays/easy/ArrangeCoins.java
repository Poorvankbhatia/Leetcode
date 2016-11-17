/*

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.

 */

package arrays.easy;

/**
 * Created by poorvank on 14/11/16.
 */
public class ArrangeCoins {

    public int arrangeCoins(int n) {

        int  k=1;
        int count = 0;

        while (n>=k) {
            count++;
            n = n-k;
            k++;
        }

        return count;

    }

}


/*

Can also be solved in O(1) time:

The idea is about quadratic equation, the formula to get the sum of arithmetic progression is
sum = (x + 1) * x / 2
so for this problem, if we know the the sum, then we can know the x = (-1 + sqrt(8 * n + 1)) / 2

public class Solution {
    public int arrangeCoins(int n) {
        return (int)((-1 + Math.sqrt(1 + 8 * (long)n)) / 2);
    }
}

 */