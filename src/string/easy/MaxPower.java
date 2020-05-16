/*
Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.

Return the power of the string.



Example 1:

Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.
Example 2:

Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
Example 3:

Input: s = "triplepillooooow"
Output: 5
Example 4:

Input: s = "hooraaaaaaaaaaay"
Output: 11
Example 5:

Input: s = "tourist"
Output: 1


Constraints:

1 <= s.length <= 500
s contains only lowercase English letters.
 */
package string.easy;

import java.util.Arrays;

public class MaxPower {

    public int maxPower(String s) {
        int uniqueCount = 0; // number of unique characters
        int n = s.length();
        // count arr to store the count of characters
        int[] count = new int[26];
        // Traverse the string, Fill the count arr
        for (int i = 0; i < n; i++) {
            if (count[s.charAt(i) - 'a'] == 0) {
                uniqueCount++;
            }
            count[s.charAt(i) - 'a']++;
        }

        // If there are not enough unique characters, show
        // an error message.
        if (uniqueCount < 1) {
            return 0;
        }

        // Otherwise take a window with first element in it.
        // start and end variables.
        int curr_start = 0, curr_end = 0;

        // Also initialize values for result longest window
        int max_window_size = 1;

        // Initialize count arr count[] with zero
        Arrays.fill(count, 0);
        count[s.charAt(0) - 'a']++;  // put the first character
        for (int i = 1; i < n; i++) {
            count[s.charAt(i) - 'a']++;
            curr_end++;
            while (!isValid(count)) {
                count[s.charAt(curr_start) - 'a']--;
                curr_start++;
            }
            if (curr_end - curr_start + 1 > max_window_size) {
                max_window_size = curr_end - curr_start + 1;
            }
        }

        return max_window_size;
    }

    boolean isValid(int[] count) {
        int val = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                val++;
            }
        }
        return (1 >= val);
    }

}

/*

public int maxPower(String s) {
        int n = s.length();
        if(n == 1) return 1;
        int start = 0, end = 0, max = 0;
        while(end < n) {
            while(end < n && s.charAt(end) == s.charAt(start)) {
                max = Math.max(max, end - start + 1);
                end++;
            }
            if(end == n) return max;
            start = end;
        }
        return max;
    }

 */