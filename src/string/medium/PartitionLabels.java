/*

A string S of lowercase letters is given. We want to partition this string into as many parts as possible
so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

 */

package string.medium;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 14/01/18.
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> currMap = new HashMap<>();
        int start = 0, end = start;
        while (end < S.length()) {
            char c = S.charAt(end);
            if (!currMap.containsKey(c)) {
                currMap.put(c, map.get(c));
            }
            currMap.put(c, currMap.get(c) - 1);
            if (currMap.get(c) == 0) {
                currMap.remove(c);
            }
            if (currMap.keySet().size() == 0) {
                res.add(end - start + 1);
                start = end + 1;
            }

            end++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij"));
    }

}
