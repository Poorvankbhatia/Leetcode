/*

Return the lexicographically smallest subsequence of text that contains all the distinct characters of text exactly once.



Example 1:

Input: "cdadabcc"
Output: "adbc"
Example 2:

Input: "abcd"
Output: "abcd"
Example 3:

Input: "ecbacba"
Output: "eacb"
Example 4:

Input: "leetcode"
Output: "letcod"


Note:

1 <= text.length <= 1000
text consists of lowercase English letters.


 */
package hashing.medium;

import java.util.HashMap;
import java.util.Map;

public class SmallestSubSequenceDistinctCharacters {

    public String smallestSubsequence(String text) {
        Map<Character,Integer> lastOccurence = new HashMap<>();
        for(int i=0;i<text.length();i++) {
            lastOccurence.put(text.charAt(i),i);
        }
        int start=0;
        int end=getMinEndingValue(lastOccurence);
        StringBuilder result = new StringBuilder();
        while(!lastOccurence.isEmpty()) {
            char k='\0';
            int next=0;
            int min = Integer.MAX_VALUE;
            for(int i=start;i<=end;i++) {
                char c = text.charAt(i);
                if(c<min && lastOccurence.containsKey(c)) {
                    min=c;
                    k=c;
                    next=i+1;
                }
            }
            result.append(k);
            lastOccurence.remove(k);
            start=next;
            end = getMinEndingValue(lastOccurence);
        }
        return result.toString();
    }

    private int getMinEndingValue(Map<Character,Integer> map) {

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character,Integer> e : map.entrySet()){
            min = Math.min(min,e.getValue());
        }
        return min;
    }

}

/*

Same as RemoveDuplicateLetters.

 */