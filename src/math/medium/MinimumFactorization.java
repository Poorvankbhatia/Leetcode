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
package math.medium;

/**
 * Created by poorvank on 23/06/17.
 */
public class MinimumFactorization {

    public int smallestFactorization(int a) {

        if(a<=9) {
            return a;
        }

        int[] count= new int[10];

        for (int i=9;i>1;i--) {
            while(a%i==0) {
                count[i]++;
                a = a/i;
            }
        }

        long result = 0;

        if (a != 1) return 0;
        for (int i=2;i<=9;i++) {
            while (count[i]>0) {
                result = (result*10) +  i;
                count[i]--;
                if(result>Integer.MAX_VALUE) {
                    return 0;
                }
            }
        }

        return (int) result;

    }

}

/*

Use larger one don't use multiple smaller ones. e.g if we can use 6 don't use 2 and 3 instead.
Then we construct the number by putting smaller digits @ Most significat digits



 */