/*

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet
 is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are
 sorted lexicographicaly in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical
 rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character.


Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

 */
package string.easy;

public class AlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] count = new int[26];
        int val=0;
        for(char c : order.toCharArray()) {
            count[c-'a']=val++;
        }
        int n = words.length;
        for(int i=n-1;i>0;i--) {
            if(compare(words[i],words[i-1],count)>0) {
                return false;
            }
        }

        return true;

    }

    private int compare(String s1,String s2,int[] count) { //
        int n = s1.length(), m = s2.length();

        for(int i = 0, j = 0; i < n && j < m; i++, j++) {
            int pos1 = count[ s1.charAt(i) - 'a' ];
            int pos2 = count[ s2.charAt(j) - 'a' ];

            if (pos1 != pos2)
                return pos2 - pos1;
        }
        return m - n;
    }
}
