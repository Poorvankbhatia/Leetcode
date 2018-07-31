/*
Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.

 */
package string.medium;

/**
 * Created by poorvank.b on 19/04/18.
 */
public class OneEditDistance {

    public boolean isOneEditDistance(String s1,String s2) {

        if(s1.length()!=s2.length() && s1.length()!=s2.length()+1 && s2.length()!=s1.length()+1) {
            return false;
        }

        if(s1.length()==s2.length()) {
            for (int i=0;i<s1.length();i++) {
                if(s1.charAt(i)!=s2.charAt(i)) {
                    return s1.substring(i+1).equals(s2.substring(i+1));
                }
            }
            return false;
        } else {
            String shorter = s1;
            String longer = s2;
            if(s1.length()>s2.length()) {
                shorter=s2;
                longer=s1;
            }
            for (int i=0;i<shorter.length();i++) {
                if(shorter.charAt(i)!=longer.charAt(i)) {
                    return shorter.substring(i).equals(longer.substring(i + 1));
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        System.out.println(new OneEditDistance().isOneEditDistance("ggss","ghs"));
    }

}
