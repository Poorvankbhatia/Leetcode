/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters
a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:

Input: s = "abc"
Output: 1


Constraints:

3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */
package string.medium;

public class SubstringsContainingAllThreeCharacters {

    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int ans = 0;
        int start = -1;
        for (int end = 0; end < s.length(); end++) {
            count[s.charAt(end) - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                ans += s.length() - end; // number of valid substrings all start from lo + 1, but end at hi, hi + 1, ..., s.length() - 1, respectively.
                start++;
                count[s.charAt(start) - 'a']--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringsContainingAllThreeCharacters().numberOfSubstrings("abc"));
    }

}

/*


Maintain a sliding window (lo, hi], where lower bound exclusively and upper bound inclusively;
Traverse string s, use upper bound hi to count the number of the 3 characters, a, b, & c;
once the sliding window includes all of the 3, we find s.length() - hi substrings (lo, hi], (lo, hi + 1], ..., (lo, s.length() - 1];
Increase the lower bound lo by 1 (denote it as lo'), decrease the count accordingly,
if the sliding window still includes all of the 3 characters, we count in substrings (lo'], (lo', hi + 1], ..., (lo', s.length() - 1];
Repeat 3 till the sliding window short of at least 1 of the 3 characters, go to step 2;
Repeat 2 - 4 till the end of the string s.

Another method:

Take three pointers l1, l2, l3 for a, b and c respectively.
Now as you iterate over the string of length n, you can count the number of sub-strings ending at that particular index.
How can we do that is here ->

Keep on updating l1, l2 and l3.
And take the minimum of l1, l2 and l3.
Now from this min-index (min(l1, l2, l3) to the curr index i this is the smallest possible sub-string ending at curr-index i which follows the constraint.
If the smallest sub-string is from min-index to curr-index, then for every sub-string starting from index 0, 1, 2, 3, ......min-index
and ending at curr-index is valid, (So how many are these...... So there are min-index + 1 sub-strings)
Add this min-index + 1 to the count.


class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length(), count = 0;
        int l1 = -1, l2 = -1, l3 = -1;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a') l1 = i;
            else if(s.charAt(i) == 'b') l2 = i;
            else l3 = i;
            if(l1 == -1 || l2 == -1 || l3 == -1) continue;
            int min = Math.min(l1, Math.min(l2, l3));
            count += min + 1;
        }
        return count;
    }
}

 */