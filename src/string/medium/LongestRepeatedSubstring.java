/*

Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500

 */
package string.medium;

import java.util.Arrays;

public class LongestRepeatedSubstring {

    public int longestRepeatingSubstring(String S) {
        int l = S.length();
        String[] suffix = new String[l];
        for (int i = 0; i < l; i++) suffix[i] = S.substring(i);
        Arrays.sort(suffix);
        int max = 0;
        for (int i = 1; i < l; i++) {
            int j = 0;
            for (; j < Math.min(suffix[i].length(),suffix[i-1].length()); j++) {
                if (suffix[i].charAt(j) != suffix[i-1].charAt(j)) break;
            }
            max = Math.max(max,j);
        }
        return max;
    }

}

/*

O (n^3) Approach 1.
loop result from max to 1, once found a max, then return

public int longestRepeatingSubstring(String S) {
	Set<String> set = new HashSet<>();
	int max = S.length() - 1, i = 0;
	for (;i <= S.length();i++) {
		int j = i;
		if (j + max > S.length()) {
			if (--max == 0) break;
			i = -1;
			set.clear();
			continue;
		}
		String k = S.substring(j,j+max);
		if (!set.add(k)) {
			return max;
		}
	}
	return max;
}
O (n^3) Approach 2.
loop result from 1 to max, once meet a ans with not found duplicate string, then return ans - 1

public int longestRepeatingSubstring(String S) {
	int l = S.length(), max = 0, i = 0;
	Set<String> s = new HashSet<>();
	while (i <= l) {
		int j = i;
		if (j + max == l) {
			return max;
		}
		String k = S.substring(j,j + max + 1);
		if (!s.add(k)) {
			i = 0;
			s.clear();
			max++;
		} else {
			i++;
		}
	}
	return max;
}
O (n^2 log n) Approach 1
get n suffix of the string. "abc" -> "abc","bc","c"
then sort them, if have two common prefix, they must be neighbors. for loop them find longest common prefix.

public int longestRepeatingSubstring(String S) {
	int l = S.length();
	String[] suffix = new String[l];
	for (int i = 0; i < l; i++) suffix[i] = S.substring(i);
	Arrays.sort(suffix);
	int max = 0;
	for (int i = 1; i < l; i++) {
		int j = 0;
		for (; j < Math.min(suffix[i].length(),suffix[i-1].length()); j++) {
			if (suffix[i].charAt(j) != suffix[i-1].charAt(j)) break;
		}
		max = Math.max(max,j);
	}
	return max;
}
O (n^2 log n) Approach 2
binary search -> if length 3 have duplicate pattern, length 2 must have.
so if we can search the answer,s = mid + 1. if not , e = mid - 1.
max is 's - 1';

public int longestRepeatingSubstring(String S) {
	char[] cs = S.toCharArray();
	int s = 1, e = cs.length - 1;
	while (s <= e) {
		int mid = (s + e) / 2;
		if (search(cs,mid)) {
			s = mid + 1;
		} else {
			e = mid - 1;
		}
	}
	return s - 1;
}
boolean search(char[] cs,int k) {
	Set<String> s = new HashSet<>();
	for (int i = 0; i <= cs.length - k; i++) {
		if (!s.add(new String(cs,i,k)))
			return true;
	}
	return false;
}
O (n^2 ) Approach 1
dp[i][j] means end with i, end with j , what's max length of common string.
abcbc. dp[2][4] = 2 because bc == bc, abc != cbc

public int longestRepeatingSubstring(String S) {
	int l = S.length();
	int[][] dp = new int[l+1][l+1];
	int res = 0;
	for (int i = 1; i <= l; i++) {
		for (int j = i + 1; j <= l; j++) {
			if (S.charAt(i - 1) == S.charAt(j - 1)) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
				res = Math.max(dp[i][j],res);
			}
		}
	}
	return res;
}
O (n^2 ) Approach 2
we could use MSD radix sort, we can sort n string , in O(26 * N * N)

public int longestRepeatingSubstring(String S) {
        int l = S.length();
        String[] suffix = new String[l];
        for (int i = 0; i < l; i++) suffix[i] = S.substring(i);
        msdRadixSort(suffix);
        int max = 0;
        for (int i = 1; i < l; i++) {
            int j = 0;
            for (; j < Math.min(suffix[i].length(),suffix[i-1].length()); j++) {
                if (suffix[i].charAt(j) != suffix[i-1].charAt(j)) break;
            }
            max = Math.max(max,j);
        }
        return max;
    }
    void msdRadixSort(String[] input) {
        sort(input, 0, input.length - 1, 0, new String[input.length]);
    }
    private void sort(String[] input, int lo, int hi, int depth, String[] aux) {
        if (lo >= hi) return;
        int[] cnt = new int[28];
        for (int i = lo; i <= hi; i++) {
            cnt[charAt(input[i], depth) + 1]++;
        }
        for (int i = 1; i < 28; i++) cnt[i] += cnt[i - 1];
        for (int i = lo; i <= hi; i++) {
            aux[cnt[charAt(input[i], depth)]++] = input[i];
        }
        for (int i = lo; i <= hi; i++) input[i] = aux[i - lo];
        for (int i = 0; i < 27; i++)
            sort(input, lo + cnt[i], lo + cnt[i + 1] - 1, depth + 1, aux);
    }
    private int charAt(String str, int i) {
        if (i >= str.length()) return 0;
        return str.charAt(i) - 'a' + 1;
    }
O (n logn ) Approach
binary search with rolling hash

public int longestRepeatingSubstring(String S) {
	char[] cs = S.toCharArray();
	int s = 1, e = cs.length - 1;
	while (s <= e) {
		int mid = (s + e) / 2;
		if (search(cs,mid)) {
			s = mid + 1;
		} else {
			e = mid - 1;
		}
	}
	return s - 1;
}
boolean search(char[] cs,int k) {
	Set<Integer> s = new HashSet<>();
	long mod = 1000000007, pow = 1, cur = 0;
	for (int i = 0; i < cs.length; i++) {
		cur = (cur * 26 + (cs[i] - 'a')) % mod;
		if (i >= k) {
			cur = (cur - ((cs[i - k] - 'a') * pow % mod) + mod) % mod;
		} else {
			 pow = pow * 26  % mod;
		}
		if (i >= k - 1) {
			if (!s.add((int)cur)) return true;
		}
	}
	return false;
}
average O (n ) Approach
use Ukkonen’s algorithm to build the suffix array in O(n)
then compare i and i+1, i from 0 to n - 2; in worst case, this algorithm toke O(n^2), because the time compare two neighbor
suffix cost O(n), there are n neighbor, so is O(n^2), but if string is random, there have no this problem.

worst case O (n ) Approach
use Ukkonen’s algorithm to build the suffix tree in O(n)
then find deepest internal node that is the answer, find deepest internal node cost O(n)
so totally O(n)

 */