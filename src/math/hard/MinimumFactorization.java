/*

Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48
Output:
68
Example 2
Input:

15
Output:
35

 */
package math.hard;

/**
 * Created by poorvank.b on 31/12/17.
 */
public class MinimumFactorization {

    public int smallestFactorization(int a) {
        if (a < 2)
            return a;
        long res = 0, mul = 1;
        for (int i = 9; i >= 2; i--) {
            while (a % i == 0) {
                a /= i;
                res = mul * i + res;
                mul *= 10;
            }
        }
        return a < 2 && res <= Integer.MAX_VALUE ? (int)res : 0;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumFactorization().smallestFactorization(35));
    }

}

/*

e know that the final number generated, resres, should be such that its digits should have a product equal to the given number aa.
In other words, the digits of resres will be the factors of the given number aa. Thus, our problem reduces to finding the factors
(not necessarily prime) of aa and finding their smallest possible arrangement. Thus, we start with trying with the largest possible factor 99,
obtain as many such counts of this factor as possible in resres and place such factors obtianed at its least significant positions.
Then, we go on decrementing the number currently considered as the possible factor and if it is a factor, we keep on placing it at relatively
more significant positions in resres. We go on getting such factors till we are done considering all the numbers from 9 to 2.
At the end, resres gives the required result.

 */