/*

Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same
digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
Example 2:
Input: 21
Output: -1

 */
package math.medium;

import java.util.Arrays;

/**
 * Created by poorvank.b on 09/04/17.
 */
public class NextGreatestNo {

    public int nextGreaterElement(int n) {

        String s = String.valueOf(n);

        int i,len=s.length();

        for (i = s.length() - 1; i > 0; i--) {
            if (Character.getNumericValue(s.charAt(i)) > Character.getNumericValue(s.charAt(i - 1))) {
                break;
            }
        }

        if (i == 0) {
            return -1;
        }

        int minIndex = i;
        int x = Character.getNumericValue(s.charAt(i-1));

        for (int j = i+1; j < len; j++)
            if (Character.getNumericValue(s.charAt(j)) > x && Character.getNumericValue(s.charAt(j)) < Character.getNumericValue(s.charAt(minIndex)))
                minIndex = j;

        String replacedString = replacedString(s, i-1, minIndex);

        char[] chars = replacedString.toCharArray();
        Arrays.sort(chars, i, s.length());

        if(isInteger(String.copyValueOf(chars))) {
            return Integer.parseInt(String.copyValueOf(chars));
        } else {
            return -1;
        }

    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    private static String replacedString(String s, int i, int j) {

        StringBuilder sb = new StringBuilder(s);
        char c = s.charAt(i);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, c);

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(new NextGreatestNo().nextGreaterElement(12443322));
    }
}
