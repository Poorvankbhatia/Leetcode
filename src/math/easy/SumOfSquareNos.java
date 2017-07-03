/*

Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

 */
package math.easy;

/**
 * Created by poorvank on 02/07/17.
 */
public class SumOfSquareNos {

    public boolean judgeSquareSum(int c) {

        for (int i=0;i<=(int) Math.sqrt(c);i++) {
            if(isSquare(c-(int) Math.pow(i,2))) {
                return true;
            }
        }

        return false;
    }


    private boolean isSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt*sqrt==n;
    }

    public static void main(String[] args) {
        System.out.print(new SumOfSquareNos().judgeSquareSum(1));
    }

}
