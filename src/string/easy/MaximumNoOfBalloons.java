/*
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

Example 1:

Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0


Constraints:

1 <= text.length <= 10^4
text consists of lower case English letters only.
 */
package string.easy;

public class MaximumNoOfBalloons {

    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26], cntBaloon = new int[26];
        for (int i = 0; i < text.length(); ++i)
            ++cnt[text.charAt(i) - 'a'];
        int min = text.length();
        for (char c : "balloon".toCharArray())
            ++cntBaloon[c - 'a'];
        for (char c : "balloon".toCharArray())
            min = Math.min(min, cnt[c - 'a'] / cntBaloon[c - 'a']);
        return min;
    }

}
