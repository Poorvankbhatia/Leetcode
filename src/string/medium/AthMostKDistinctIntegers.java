/*

Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.


Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50

 */

package string.medium;

import java.util.HashMap;
import java.util.Map;

public class AthMostKDistinctIntegers {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character,Integer> freq = new HashMap<>();
        int start = 0;
        int end = 0;
        int distinct = 0;
        int max = Integer.MIN_VALUE;
        while(end<s.length()) {
            if(freq.getOrDefault(s.charAt(end),0)==0) {
                distinct++;
            }
            freq.put(s.charAt(end),freq.getOrDefault(s.charAt(end),0)+1);
            end++;
            while(start<end && distinct>k) {
                if(freq.get(s.charAt(start))==1) {
                    distinct--;
                }
                freq.put(s.charAt(start),freq.get(s.charAt(start))-1);
                start++;
            }
            max = Math.max(max,end-start);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new AthMostKDistinctIntegers().lengthOfLongestSubstringKDistinct("abee",1));
    }

}

/*

See Also

SubArraysWithKDistinctIntegers
NiceSubArrays


Possible follow up.
The interviewer may say that the string is given as a stream.
In this situation, we can't maintain a "left pointer" as the classical O(n) hashmap solution.

We can no longer use s.charAt(lp) because lp could be infinitely far back
(K maybe small, but you could have a shitton of repeating characters),
in which case storing the entire, infinite window in memory would be impossible, which breaks s.charAt(lp).

The key idea is that you can't assume any portion of the input string is stored in memory.


public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int lo = 0, hi = 0;
        int n = s.length();
        int max = 0;
        if (k == 0) return 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        while (hi < n) {
            char ch = s.charAt(hi);
            if (map.containsKey(ch) || map.size() < k) {
                map.remove(ch);
                map.put(ch, hi);
                max = Math.max(max, hi++ - lo + 1);
            }
            else {
                Character key = map.keySet().iterator().next();
                lo = map.get(key);
                map.remove(key);
                lo++;
            }
        }
        return max;
    }


 */