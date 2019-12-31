/*

A positive integer is magical if it is divisible by either A or B.

Return the N-th magical number.  Since the answer may be very large, return it modulo 10^9 + 7.



Example 1:

Input: N = 1, A = 2, B = 3
Output: 2
Example 2:

Input: N = 4, A = 2, B = 3
Output: 6
Example 3:

Input: N = 5, A = 2, B = 4
Output: 10
Example 4:

Input: N = 3, A = 6, B = 4
Output: 8


Note:

1 <= N <= 10^9
2 <= A <= 40000
2 <= B <= 40000


 */
package binarysearch.hard;

public class NthMagicalNumber {

    int mod = (int)(Math.pow(10,9))+7;
    private int gcd(int a,int b) {
        if(a==0) {
            return b;
        }
        return gcd(b%a,a);
    }

    private int lcm(int a,int b) {
        return (a*b)/gcd(a,b);
    }

    private long count(long num,int A,int B,int lcm) {
        return num/A+num/B-(num/lcm);
    }

    public int nthMagicalNumber(int N, int A, int B) {
        long lo = 0;
        long hi = Long.MAX_VALUE;
        int lcm = lcm(A,B);
        while(lo<hi) {
            long mid = lo+(hi-lo)/2;
            long c = count(mid,A,B,lcm);
            if(c>=N) { // We don't do c==N return mid as we dont know if mid is divisible by one of a or b.
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return (int) (lo%mod);

    }

    public static void main(String[] args) {
        System.out.println(new NthMagicalNumber().nthMagicalNumber(100000000,40000,40000));
    }

}
