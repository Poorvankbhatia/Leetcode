/*
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
word contains the first letter of puzzle.
For each letter in word, that letter is in puzzle.
For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed"
(doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].


Example :

Input:
words = ["aaaa","asas","able","ability","actt","actor","access"],
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa"
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.


Constraints:

1 <= words.length <= 10^5
4 <= words[i].length <= 50
1 <= puzzles.length <= 10^4
puzzles[i].length == 7
words[i][j], puzzles[i][j] are English lowercase letters.
Each puzzles[i] doesn't contain repeated characters.
 */
package string.hard;

import java.util.*;

public class ValidWordsForEachPuzzle {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        Map<Integer,Integer> map = new HashMap<>();
        for (String word : words ) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask = mask | 1<<(c-'a');
            }
            map.put(mask,map.getOrDefault(mask,0)+1);
        }
        List<Integer> result = new ArrayList<>();
        for (String puzzle : puzzles) {
            int mask = 0;
            for (char c : puzzle.toCharArray()) {
                mask = mask | 1 << (c - 'a');
            }
            int count = 0;
            int sub = mask;
            int first = 1 << (puzzle.charAt(0) - 'a');

            while (sub != 0) {

                if ((sub & first) == first && map.containsKey(sub)) {
                    count += map.get(sub);
                }
                sub = (sub - 1) & mask; // get the next substring

            }

            result.add(count);

        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = new String[]{"abslute"};
        System.out.println(new ValidWordsForEachPuzzle().findNumOfValidWords(words,puzzles));

    }

}

/*

bitmap

Since all input are lower case alphabet, it can be represented as 32 bit integer (alphabet size = 26).
In the beginning, we find all such binary strings of words, and store them in hash table along with their frequencies
(it shows number of words with same characters, might have different length but contain same characters).

Now one by one, create binary string for each word in puzzle. To be valid, it must contain first character of puzzle word.
So we check (sub&first). The reason for (sub&first == first) is that bitwise AND gives 32 bit binary integer.
So regardless of other set bit, if first character bit is set and is in map, increment count with corresponding map value.

According to Kerninghan's theorm n&(n-1) reset last set bit. For eg. 3 = 0011, 2 = 0010. 3&2 = 0010. Last set bit is reset.
Here, if we take bit wise AND with (sub-1), it will reset that bit to give next lower sub string.


First of all, create a map to cache the frequency of the encoded word.

Next, loop the puzzles. During the inner loop, instead of loop through map's keyset (which cause TLE), use sub = (sub - 1) & mask
to find all possible char combinations of current puzzel.

We need update count only if combination contains first element of puzzle as well as map has a record of it
(means this puzzle's char combination is as the same as one of the encoded word).

Time Compliexity: O( n * 2 ^ 7 + m * k) = O(n + mk)
n = len(puzzles);
m = len(words);
k = len(single word)

 */