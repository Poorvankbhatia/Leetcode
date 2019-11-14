/*
Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.



Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1


Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.
 */
package string.medium;

public class SwapLargestRepeatedCharacter {

    public int maxRepOpt1(String s) {
        int[] count = new int[26];
        int[] freq = new int[26];
        for(char c : s.toCharArray()) {
            freq[c-'a']++;
        }
        int start=0;
        int len=0;int max=0;
        int end=0;
        char maxChar = s.charAt(0);
        while(end<s.length()) {
            char c = s.charAt(end);
            count[c-'a']++;
            if(max<count[c-'a']) {
                max = count[c-'a'];
                maxChar = s.charAt(end);
            }
            if((end-start+1)-max>1) {
                count[s.charAt(start)-'a']--;
                start++;
            }
            end++;
            len = Math.max(len,end-start);
        }
        return Math.min(len,freq[maxChar-'a']);
    }




    public static void main(String[] args) {
        System.out.println(new SwapLargestRepeatedCharacter().maxRepOpt1("aaabbaaa"));
    }


}

/*

SIMILAR TO: LongestRepeatedCharacterReplacement

 */
