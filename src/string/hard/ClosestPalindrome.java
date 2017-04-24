/*

Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.

 */
package string.hard;

/**
 * Created by poorvank on 24/04/17.
 */
public class ClosestPalindrome {

    public String nearestPalindromic(String n) {
        if (n.length() >= 2 && checkNine(n)) {
            String s = "1";
            for (int i = 0; i < n.length() - 1; i++) {
                s += "0";
            }
            s += "1";
            return s;
        }
        boolean hasOddLength = (n.length() % 2 != 0);
        String left = n.substring(0, (n.length() + 1) / 2);
        long[] increment = {-1, 0, +1};
        String ret = n;
        long minDiff = Long.MAX_VALUE;
        for (long i : increment) {
            String s = getPalindrome(Long.toString(Long.parseLong(left) + i), hasOddLength);
            if (n.length() >= 2 && (s.length() != n.length() || Long.parseLong(s) == 0)) {
                s = "";
                for (int j = 0; j < n.length() - 1; j++) {
                    s += "9";
                }
            }
            long diff = s.equals(n) ? Long.MAX_VALUE : Math.abs(Long.parseLong(s) - Long.parseLong(n));
            if (diff < minDiff) {
                minDiff = diff;
                ret = s;
            }
        }
        return ret;
    }
    private String getPalindrome(String s, boolean isOdd) {
        String right = new StringBuilder(s).reverse().toString();
        return isOdd ? s.substring(0, s.length() - 1) + right : s + right;
    }
    private boolean checkNine(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "100";
        System.out.println(new ClosestPalindrome().nearestPalindromic(s));
    }

}

/*

We first need to find the higher palindrome and lower palidrome respectively. and return the one who has the least different with the input number.
For the higher palindrome, the low limit is number + 1 while for the lower palindrome, the high limit is number - 1.


 */