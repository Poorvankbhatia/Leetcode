/*

Given two strings S and T, determine if they are both one edit distance apart.

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
