/*
Given two positive integers n and k.

A factor of an integer n is defined as an integer i where n % i == 0.

Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.



Example 1:

Input: n = 12, k = 3
Output: 3
Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
Example 2:

Input: n = 7, k = 2
Output: 7
Explanation: Factors list is [1, 7], the 2nd factor is 7.
Example 3:

Input: n = 4, k = 4
Output: -1
Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
Example 4:

Input: n = 1, k = 1
Output: 1
Explanation: Factors list is [1], the 1st factor is 1.
Example 5:

Input: n = 1000, k = 3
Output: 4
Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000].
 */
package math.medium;
import java.util.TreeSet;

public class KthFactorN {

    public int kthFactor(int n, int k) {

        TreeSet<Integer> factors = new TreeSet<>();
        for (int i=1;i<=(int)(Math.sqrt(n));i++) {
            if(n%i==0) {
                if(i*i!=n) {
                    factors.add(i);
                    factors.add(n/i);
                    continue;
                }
                factors.add(i);
            }
        }
        Integer ans = -1;
        while (k>0 && factors.size()>1) {
            ans=factors.pollFirst();
            k--;
        }
        return k>0?-1:ans;
    }

    public static void main(String[] args) {
        System.out.println(new KthFactorN().kthFactor(7,2));
    }

}

/*

Better sol:

 public int kthFactor(int n, int k) {
         int d = (int)(Math.sqrt(n));
        for(int i = 1; i <= d; i++){
            if(n % i == 0 && --k == 0) return i;
        }
       if(d*d==n) {
           d--;
       }
        for(int i = d; i > 0; i--){
            if(n % i == 0 && --k == 0) return n / i;
        }
        return -1;
    }

 */
