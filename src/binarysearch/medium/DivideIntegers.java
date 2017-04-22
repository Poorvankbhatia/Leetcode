package binarysearch.medium;

/**
 * Created by poorvank on 27/03/17.
 */
public class DivideIntegers {

    public int divide(int dividend, int divisor) {

        if(divisor==0) {
            return Integer.MAX_VALUE;
        }

        if(dividend==0 || dividend<divisor) {
            return dividend;
        }

        int result = 0;

        while (divisor<=dividend) {
            divisor += divisor;
            result++;
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.print(new DivideIntegers().divide(10,2));
    }

}
