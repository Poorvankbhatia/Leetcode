/*

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.



 */
package string.easy;

/**
 * Created by poorvank on 07/10/16.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(null==s || s.equals("")) {
            return true;
        }
        char[] arr = s.toCharArray();

        for (int j=arr.length-1,i=0;j>=0 && i<arr.length;) {

            if(!Character.isLetterOrDigit(arr[i]) && i<arr.length) {
                i++;
            } else if(!Character.isLetterOrDigit(arr[j]) && j>=0) {
                j--;
            } else if(Character.toLowerCase(arr[i])==Character.toLowerCase(arr[j])) {
                if(i==j) {
                    return true;
                } else {
                    i++;
                    j--;
                }
            } else {
                return false;
            }
        }

        return true;

    }
}
