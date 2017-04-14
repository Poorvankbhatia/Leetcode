/*

Implement atoi to convert a string to an integer.

 */
package string.easy;

/**
 * Created by poorvank on 27/11/16.
 */
public class Atoi {

    public int myAtoi(String str) {

        if(str==null || str.length()<1) {
            return 0;
        }

        str=str.trim();

        boolean negative = false;

        int i=0;
        if(str.charAt(i)=='-') {
            i++;
            negative = true;
        } else if(str.charAt(i)=='+') {
            i++;
        }

        double result=0;

        while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }

        if(negative) {
            result = -result;
        }

        if (result > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (result < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        return (int) result;

    }

}
