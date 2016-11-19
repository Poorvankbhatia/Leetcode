/*

Find the length of the longest substring T of a given string (consists of lowercase letters only)
such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

 */
package string.medium;

/**
 * Created by poorvank on 18/11/16.
 */
public class AtleastKRepeatingChars {

    public int longestSubstring(String s, int k) {
        int[] count = new int[26];

        for (int i=0;i<s.length();i++) {
            count[s.charAt(i)-'a']++;
        }

        int pos=0,max=0;
        for (int i=0;i<s.length();i++) {
            if(count[s.charAt(i)-'a']<k) {
                max = Math.max(max,longestSubstring(s.substring(pos,i),k));
                pos = i+1;
            }
        }

        return pos==0?s.length():Math.max(max,longestSubstring(s.substring(pos),k));
    }

    public static void main(String[] args) {
        System.out.print(new AtleastKRepeatingChars().longestSubstring("llkcc",2));
    }

}


/*

Divide and conquer approach
O(nlogn)

 */