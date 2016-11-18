/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example,
Given s = "Hello World",
return 5.


 */
package string.easy;

/**
 * Created by poorvank on 17/11/16.
 */
public class LastWordlength {

    public int lengthOfLastWord(String s) {

        if(s==null || s.length()==0) {
            return 0;
        }

        //In case the string is like "a "
        s = s.trim();

        char[] arr = s.toCharArray();

        int len = 0;
        for (int i=s.length()-1;i>=0;i--) {
            if(arr[i]!=' ') {
                len++;
            } else {
                break;
            }
        }

        return len;
    }

}
