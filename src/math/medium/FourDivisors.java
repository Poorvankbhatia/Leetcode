/*
Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.

If there is no such integer in the array, return 0.



Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.


Constraints:

1 <= nums.length <= 10^4
1 <= nums[i] <= 10^5
 */
package math.medium;

import java.util.HashSet;
import java.util.Set;

public class FourDivisors {

    public int sumFourDivisors(int[] nums) {
        int res=0;
        for(int n : nums) {
            res+=count(n);
        }
        return res;
    }

    private int count(int n) {
        Set<Integer> set = new HashSet<>();
        for(int i=1;i<=(int) Math.sqrt(n);i++) {
            if(n%i==0) {
                if(n/i==i) {
                    set.add(i);
                } else {
                    set.add(i);
                    set.add(n/i);
                }
            }
        }
        if(set.size()!=4) {
            return 0;
        } else {
            int sum=0;
            for(int x: set) {
                sum+=x;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FourDivisors().sumFourDivisors(new int[]{21,4,7}));
    }

}

/*

Read:  https://www.geeksforgeeks.org/count-divisors-n-on13/

 */