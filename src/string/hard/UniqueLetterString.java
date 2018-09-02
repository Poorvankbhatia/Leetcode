/*

A character is unique in string S if it occurs exactly once in it.

For example, in string S = "LETTER", the only unique characters are "L" and "R".

Let's define UNIQ(S) as the number of unique characters in string S.

For example, UNIQ("LETTER") =  2.

Given a string S with only uppercases, calculate the sum of UNIQ(substring) over all non-empty substrings of S.

If there are two or more equal substrings at different positions in S, we consider them different.

Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.



Example 1:

Input: "ABC"
Output: 10
Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
Evey substring is composed with only unique letters.
Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
Example 2:

Input: "ABA"
Output: 8
Explanation: The same as example 1, except uni("ABA") = 1.


Note: 0 <= S.length <= 10000.

 */
package string.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 02/09/18.
 */
public class UniqueLetterString {

    private int mod = 1000000007;

    public int uniqueLetterString(String S) {

        Map<Character,List<Integer>> map = new HashMap<>();

        for (int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c,new ArrayList<>());
            }
            map.get(c).add(i);
        }

        long ans = 0;
        for (List<Integer> list : map.values()) {
            for (int i=0;i<list.size();i++) {
                long prev = i>0?list.get(i-1):-1;
                long next = i<list.size()-1?list.get(i+1):S.length();
                ans += (list.get(i) - prev) * (next - list.get(i));
            }
        }


        return (int) ans%mod;

    }

}

/*

Consider how many substrings have a specific "A". For example, let's say S only has three "A"'s, at
'S[10] = S[14] = S[20] = "A"; and we want to know the number of substrings that contain S[14].
The answer is that there are 4 choices for the left boundary of the substring (11, 12, 13, 14),
and 6 choices for the right boundary (14, 15, 16, 17, 18, 19). So in total, there are 24 substrings that have S[14] as their unique "A".

Continuing our example, if we wanted to count the number of substrings that have S[10], this would be 10 * 4 - note that
when there is no more "A" characters to the left of S[10], we have to count up to the left edge of the string.

 */