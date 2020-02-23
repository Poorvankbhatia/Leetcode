/*
Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.

Return the two integers in any order.

Example 1:

Input: num = 8
Output: [3,3]
Explanation: For num + 1 = 9, the closest divisors are 3 & 3, for num + 2 = 10, the closest divisors are 2 & 5, hence 3 & 3 is chosen.
Example 2:

Input: num = 123
Output: [5,25]
Example 3:

Input: num = 999
Output: [40,25]


Constraints:

1 <= num <= 10^9
 */
package math.medium;

public class ClosestDivisors {

    public int[] closestDivisors(int num) {
        int[] v1 = calculateClosestFactors(num+1);
        int[] v2 = calculateClosestFactors(num+2);
        return v1[1]-v2[0]<v2[1]-v2[0]?v1:v2;
    }

    private int[] calculateClosestFactors(int num) {
        int sqrt = (int)(Math.sqrt(num));
        for (int v1=sqrt;v1>=1;v1--) {
            int v2 = num/v1;
            if(num%v1==0 && num%v2==0) {
                return new int[]{v1,v2};
            }
        }
        return new int[]{0,Integer.MAX_VALUE};
    }

}
