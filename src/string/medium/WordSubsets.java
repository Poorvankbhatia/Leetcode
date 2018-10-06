/*

We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a.

Return a list of all universal words in A.  You can return the words in any order.



Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]
Example 3:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]
Example 4:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
Example 5:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]


Note:

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].

 */
package string.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 07/10/18.
 */
public class WordSubsets {

    public List<String> wordSubsets(String[] A, String[] B) {
        int[] union = new int[26];
        for (String b : B) {
            int[] count = countWords(b);
            for (int i = 0; i < 26; i++) {
                union[i] = Math.max(union[i], count[i]);
            }
        }

        List<String> res = new ArrayList<>();
        for (String a : A) {
            int[] count = countWords(a);
            int c = 0;
            for (int i = 0; i < 26; i++) {
                if (union[i] <= count[i]) c++;
            }
            if (c == 26) res.add(a);
        }

        return res;
    }

    private int[] countWords(String str) {
        int[] count = new int[26];
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        return count;
    }

}
/*

in order to judge each word in A, you have to compare it with each word in B. Why don't we preprocess B?
We can find the union set of B by one time.
For example: B = {"oa", "oob", "c"}, then the union set is "ooabc". Compare this union set with each word in A

 */
