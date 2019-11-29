/*

Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.



Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26


Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.

 */
package backtracking.medium;

import java.util.*;

public class UniqueCharacterConcatenation {

    private int result = 0;
    public int maxLength(List<String> arr) {
        dfs(arr, "", 0);
        return result;
    }

    private void dfs(List<String> arr, String path, int start) {
        boolean uniq = isUniqueChars(path);
        if (uniq) {
            result = Math.max(path.length(), result);
        }
        if (start == arr.size() || !uniq) {
            return;
        }
        for (int i = start; i < arr.size(); i++) {
            dfs(arr, path + arr.get(i), i + 1);
        }
    }

    private boolean isUniqueChars(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new UniqueCharacterConcatenation().maxLength(Arrays.asList("un","iq","ue")));
    }


}
