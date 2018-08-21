/*

You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x
in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern.

You may return the answer in any order.



Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.


Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20

 */
package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank.b on 21/08/18.
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {

        String encodedPattern = encodeString(pattern);
        List<String> result = new ArrayList<>();
        for (String word : words) {

            if (word.length() == pattern.length() && encodeString(word).equals(encodedPattern)) {
                result.add(word);
            }

        }

        return result;
    }

    private String encodeString(String str) {

        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> map = new HashMap<>();

        //For every character assign its value in the hash == first occurring position
        for (int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c,i+1);
            }

            sb.append(map.get(c));
        }

        return sb.toString();

    }

}
