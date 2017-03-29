/*

We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14

 */

package math.easy;

/**
 * Created by poorvank.b on 26/03/17.
 */
public class PerfectNo {

    public boolean checkPerfectNumber(int num) {

        if(num<=1) {
            return false;
        }

        int sum = 0;
        int square_root = (int) Math.sqrt(num) + 1;
        for (int i = 1; i < square_root; i++) {
            if (num % i == 0&&i*i!=num)
                if(i==1) {
                    sum += i;
                } else {
                    sum += i + (num/i);
                }
            if (num % i == 0&&i*i==num)
                sum += i;
        }

        return sum==num;

    }

    public static void main(String[] args) {
        System.out.print(new PerfectNo().checkPerfectNumber(78));
    }

}
