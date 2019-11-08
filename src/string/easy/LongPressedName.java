/*
Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character.


Note:

name.length <= 1000
typed.length <= 1000
The characters of name and typed are lowercase letters.
 */
package string.easy;

public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        return isSubSequence(name,typed);
    }

    private boolean isSubSequence(String s,String t) {
        if(t.length()==0 && s.length()!=0) {
            return false;
        }
        int i=0;
        while (i<s.length()) {
            int pos = t.indexOf(s.charAt(i));
            if(pos==-1){
                return false;
            } else {
                t = t.substring(pos+1);
                i++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new LongPressedName().isLongPressedName("kikcxmvzi","kiikcxxmmvvzz"));
    }

}

/*

Two pointer sol:

 public boolean isLongPressedName(String name, String typed) {
    int i = 0, m = name.length(), n = typed.length();
        for (int j = 0; j < n; ++j)
            if (i < m && name.charAt(i) == typed.charAt(j))
                ++i;
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                return false;
        return i == m;

    }

 */