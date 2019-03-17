/*

Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings
within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times,
you need to include that character three times in the final answer.

You may return the answer in any order.



Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter


 */
package string.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommonCharacters {
    public List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        if(A==null || A.length==0) {
            return list;
        }
        int[] count = new int[26];
        Arrays.fill(count,Integer.MAX_VALUE);
        for(String s : A) {
            int[] current = new int[26];
            for(char c : s.toCharArray()) {
                current[c-'a']++;
            }
            for(int i=0;i<26;i++) {
                count[i]=Math.min(count[i],current[i]);
            }
        }
        for(int i=0;i<26;i++) {
            while (count[i]-- > 0) {
                list.add("" + (char)(i + 'a'));
            }
        }
        return list;
    }
}
