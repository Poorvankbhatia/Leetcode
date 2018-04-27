/*

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.
The answer is in lowercase.

Example:
Input:
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"),
and that "hit" isn't the answer even though it occurs more because it is banned.


Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

 */

package string.easy;

import java.util.*;

/**
 * Created by poorvank.b on 28/04/18.
 */
public class BannedWords {

    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedWords = new HashSet<>();
        Collections.addAll(bannedWords, banned);

        StringBuilder sb = new StringBuilder();

        int maxFrequency = Integer.MIN_VALUE;
        Map<String,Integer> map = new HashMap<>();
        String result = "";

        for (int i=0;i<paragraph.length();i++) {
            char c = paragraph.charAt(i);
            if(Character.isLetter(c)) {
                sb.append(c);
            }
            if(!Character.isLetter(c) || i==paragraph.length()-1) {
                String s = sb.toString().toLowerCase();
                if(!s.isEmpty() && !bannedWords.contains(s)) {
                    map.put(s,map.getOrDefault(s,0)+1);
                    if(map.getOrDefault(s,0)>maxFrequency) {
                        result=s;
                        maxFrequency= map.get(s);
                    }
                }
                sb = new StringBuilder();
            }
        }


        return result;


    }

    public static void main(String[] args) {
        String[] banned = new String[]{"hit"};
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";

        System.out.println(new BannedWords().mostCommonWord(paragraph,banned));
    }

}
