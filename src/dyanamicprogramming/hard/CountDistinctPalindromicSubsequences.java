/*

Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

A subsequence of a string S is obtained by deleting 0 or more characters from S.

A sequence is palindromic if it is equal to the sequence reversed.

Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

Example 1:
Input:
S = 'bccb'
Output: 6
Explanation:
The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
Note that 'bcb' is counted only once, even though it occurs twice.
Example 2:
Input:
S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
Output: 104860361
Explanation:
There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
Note:

The length of S will be in the range [1, 1000].
Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

 */
package dyanamicprogramming.hard;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Created by poorvank.b on 16/09/18.
 */
public class CountDistinctPalindromicSubsequences {

    private int div = 1000000007;

    @SuppressWarnings("unchecked")
    public int countPalindromicSubsequences(String S) {

        if (S == null || S.length() == 0) {
            return 0;
        }

        TreeSet<Integer>[] set = new TreeSet[26];

        for (int i = 0; i < set.length; i++) {
            set[i] = new TreeSet<>();
        }

        for (int i = 0; i < S.length(); i++) {
            set[S.charAt(i) - 'a'].add(i);
        }

        Integer[][] dp = new Integer[S.length() + 1][S.length() + 1];
        return memo(set, dp, 0, S.length());

    }

    private int memo(TreeSet<Integer>[] sets, Integer[][] dp, Integer start, Integer end) {

        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != null) {
            return dp[start][end];
        }

        long ans = 0L;

        for (int i = 0; i < 26; i++) {
            if (sets[i].size() == 0) {
                continue;
            }
            Integer newStart = sets[i].ceiling(start); // Return null.
            Integer newEnd = sets[i].lower(end);
            if (newStart == null || newStart >= end) {
                continue;
            }
            if (!Objects.equals(newStart, newEnd)) {
                ans += 2;
            } else {
                ans += 1;
            }
            ans += memo(sets, dp, newStart + 1, newEnd);
        }

        dp[start][end] = (int) (ans % div);
        System.out.println(start +" " + end + " " + ans);
        return dp[start][end];

    }

    public static void main(String[] args) {
        System.out.println(new CountDistinctPalindromicSubsequences().countPalindromicSubsequences("ccb"));
    }

}

/*

This question gives us a string, let us find the number of all non-empty palindrome sub-sequences. Although this question restricts only
four characters, we still solve it according to the general situation. 26 letters. Then say that the final result is to take a large number,
which implies that the result will be a large value, then the problem is usually solved by DP or recursion with memory array memo, both
The essence is actually the same.

Let's first look at the recursive solution with memory array memo. The idea of ​​this solution is to peel
off the onion layer by layer, such as "bccb", strip it according to the letter, first strip the letter b, and determine the outermost
layer "b _ _ b"
This will generate two palindrome subsequences "b" and "bb", then recursively into the middle part, calculate the number
of palindrome subsequences in the middle and add them to the result res, then start stripping the letter c and find the outermost layer. "cc",
at this time will produce two palindrome sub-sequences "c" and "cc", and then there is no string in the middle, so recursively returns 0,
in this way you can calculate all the palindrome sub-sequences.

We create a two-dimensional array of chars with an outer length of 26 and an empty array inside. This is to count the position of each
letter in the original string, and then define a two-dimensional memory array memo, where memo[i][j] represents the substring between
the i-th character and the j-th character. The number of palindrome subsequences is initialized to 0. Then we loop through the string
S and add the position of each character to its corresponding array. For example, for "bccb", then:

b -> {0, 3}

c -> {1, 2}

Then call the recursive function in the range of [0, n].
In the recursive function, first determine if start is greater than or equalto end, return 0.
If the value of the current position in memo is greater than 0, it indicates that the current situation has been calculated,
and directly returns the value in the memo array.

Otherwise, all letters are traversed. If there is no value in the array corresponding to a
letter, it means that the letter does not appear in the string, skipping.
Then we find the first position in the alphabet array not less than
start, find the first position less than end, in the current loop, start is 0, end is 4, the current letter b, our new_start points to 0,
new_end points 3.
If the current new_start points to end(), or the position it points to is greater than end, it means that there is no
letter b in the current range, skip it directly, otherwise the result res is incremented by 1, because new_start exists at this time,
at least one single letter exists. b, can also be used as a palindrome subsequence, then see if new_start and new_end are not the same,
indicating that the two point to a different b, then res should be incremented by 1, because a new palindrome subsequence "bb" has been
added.
The following is to call the recursive function on the middle part, and add the return value to the result res. At this point,
the letter b is processed. Now the letter c is processed. At this time, the start is still 0, the end is still 4, the new_start points to 1,
and the new_end points to 2. The same as the above analysis, the new_start is in the range, and the result is incremented by 1,
because On the "c", then new_start and new_end are different, the result res is incremented by 1, because "cc" is added, there is no character
in between, the result of calling recursion is 0, the for loop ends, we will memo[start] The value of [end] is reserved for the super large
number, and the value can be returned.

It will first check the string [a...a, b...b, c...c, d...d], then goes to next level.
For palindrome a...a, in next level, it will check [aa...a, ab...a, ac...a, ad...a].

Time complexity : (N^2)*logN.

https://leetcode.com/problems/count-different-palindromic-subsequences/discuss/112757/Java-solution-using-simple-DP.-O(n2)-run-time-and-O(n2)-space

 */