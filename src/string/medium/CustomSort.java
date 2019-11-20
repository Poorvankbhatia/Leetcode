/*

S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted.
More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.

 */
package string.medium;

/**
 * Created by poorvank.b on 27/02/18.
 */
public class CustomSort {

    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for(char c: T.toCharArray()) {
            count[c-'a']++;
        }
        StringBuilder result = new StringBuilder();
        for(char c :S.toCharArray()) {
            while(count[c-'a']-->0) {
                result.append(c);
            }
        }
        for(int i=0;i<26;i++) {
            while(count[i]-->0) {
                result.append((char)(i+'a'));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new CustomSort().customSortString("cba","abcd"));
    }

}


/*

O(N+M) time complexity

 */