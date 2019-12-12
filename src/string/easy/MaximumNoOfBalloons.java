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
        int[] sCount = getCount("balloon");
        int[] tCount = getCount(text);
        int min = Integer.MAX_VALUE;
        for(int i=0;i<26;i++) {
            if(sCount[i]!=0) {
                if(tCount[i]<sCount[i]) {
                    return 0;
                } else {
                    min = Math.min(min,tCount[i]/sCount[i]); // if b occurs 3 times but o only occurs 4 then answer should be 2
                }
            }
        }
        return min;
    }

    private int[] getCount(String s) {
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c-'a']++;
        }
        return count;
    }

}
