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

    public static void main(String[] args) {
        String[] strings  = new String[]{"abc","a"};
        System.out.print(new LongestCommonPrefix().longestCommonPrefix(strings));
    }

}

/*

Time Complexity : Since we are iterating through all the strings and for each string we are iterating though each characters,
so we can say that the time complexity is O(N M) where,

N = Number of strings
M = Length of the largest string string

Time complexity : O(S) , where S is the sum of all characters in all strings.
In the worst case all n strings are the same. The algorithm compares the string S1 with the other strings [S_2.... S_n]
There are S character comparisons, where S is the sum of all characters in the input array.

Space complexity : O(1). We only used constant extra space.

 */