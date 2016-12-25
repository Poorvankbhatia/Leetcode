/*

Write a function to find the longest common prefix string amongst an array of strings.

 */
package string.easy;

/**
 * Created by poorvank on 15/11/16.
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {

        if(strs==null || strs.length==0) {
            return "";
        }

        String prefix = strs[0];

        for (int i=1;i<strs.length;i++) {
            while (strs[i].indexOf(prefix)!=0) {
                prefix = prefix.substring(0,prefix.length()-1);
                if(prefix.length()==0) {
                    return "";
                }
            }
        }

        return prefix;

    }

}
