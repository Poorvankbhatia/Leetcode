/*

In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 10^9 + 7.

Example 1:
Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].

Note:
n is in the range of [1, 10^6].

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 02/07/17.
 */
public class Derangement {

    public int findDerangement(int n) {
        int mod = (int) (Math.pow(10,9)+7);

        if(n==0 || n==1) {
            return 0;
        }

        long[] res = new long[n+1];
        res[0] = res[1] =  0;
        res[2] = 1;

        for (int i=3;i<=n;i++) {
            res[i] = ((i-1)%mod*((res[i-1]+res[i-2])%mod))%mod;
            if(res[i]>Integer.MAX_VALUE) {
                res[i] %=mod;
            }
        }


        return (int) res[n];

    }

    public static void main(String[] args) {
        System.out.print(new Derangement().findDerangement(13));
    }

}
