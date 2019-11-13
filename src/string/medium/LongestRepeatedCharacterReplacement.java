/*

Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most
k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

 */
package string.medium;

/**
 * Created by poorvank on 16/12/16.
 */
public class LongestRepeatedCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int start=0;
        int len=0;int max=0;
        int end=0;
        while(end<s.length()) {
            char c = s.charAt(end);
            count[c-'A']++;
            max = Math.max(max,count[c-'A']);
            if((end-start+1)-max>k) {
                len = Math.max(len,end-start);
                count[s.charAt(start)-'A']--;
                start++;
            }
            end++;
        }
        len = Math.max(len,end-start);
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new LongestRepeatedCharacterReplacement().characterReplacement("AB",1));
    }

}

/*

The problem is similar to longest substring with most K distinct characters. But this time, the constraint is we can only have
 most K characters that is different with the most frequent character in the substring. For example in the sliding window:
"ABBBAC" most frequent character is B with count 3, all other character is count as different to B,
    which is A and C, and the result is 2 + 1 = 3.
Each time we count the different characters. If it is not bigger than k we extend the sliding window.
Since we only have 26 characters, keep the count in a integer array is good enough.

The problem says that we can make at most k changes to the string (any character can be replaced with any
other character). So, let's say there were no constraints like the k. Given a string convert it to a string with
all same characters with minimal changes. The answer to this is
length of the entire string - number of times of the maximum occurring character in the string
Given this, we can apply the at most k changes constraint and maintain a sliding window such that
(length of substring - number of times of the maximum occurring character in the substring) <= k



 */