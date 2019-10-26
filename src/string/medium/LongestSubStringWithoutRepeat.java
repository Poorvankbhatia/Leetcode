/*

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
package string.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LongestSubStringWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int start = 0,end=0;
        Map<Character,Integer> map = new HashMap<>();
        int len=0;
        while(end<n) {
            char c = s.charAt(end);
            if(!map.containsKey(c) ||
                    /*For input like "tmmzuxt"*/
                    (map.getOrDefault(c,0)<start)) {
                len = Math.max(len,end-start+1);
            } else {
                start = map.get(c)+1;
            }
            map.put(c,end);
            end++;
        }
        return len;
    }
}

/*

public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) {
            return 0;
        }
        Queue<Character> queue = new LinkedList<>();
        int res = 0;
        for (char c : s.toCharArray()) {
            while (queue.contains(c)) {
                queue.poll();
            }
            queue.offer(c);
            res = Math.max(res, queue.size());
        }
        return res;
    }

public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) {
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();
        int start=0,end=0,maxLength=0;
        while(end<s.length()) {
            if(map.containsKey(s.charAt(end))) {
                start = Math.max(start,map.get(s.charAt(end))+1);
            }
            map.put(s.charAt(end),end);
            maxLength=Math.max(end-start+1,maxLength);
            end++;
        }
        return maxLength;
    }

 */