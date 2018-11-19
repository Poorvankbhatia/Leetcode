/*

Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.


Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"


Note:

1 <= A.length <= 12
1 <= A[i].length <= 20


 */
package string.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 19/11/18.
 */
public class ShortestSuperstring {

    public String shortestSuperstring(String[] A) {
        int n = A.length;

        char[] used = new char[n];
        Arrays.fill(used, '1');
        Map<String, String> map = new HashMap<>();
        return findShortestSuperString(A, used, n, map);
    }

    private String findShortestSuperString(String[] A, char[] used, int cnt, Map<String, String> map) {
        //cnt is the number of available words left;
        int n = A.length;

        String key = new String(used);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        //if cnt is 1, then the shortest string can formed is just that word.
        if (cnt == 1) {
            for (int i = 0; i < n; i++) {
                if (used[i] == '1') {
                    map.put(key, A[i]);
                }
            }
            return map.get(key);
        } else {
            //if there are more then one available words left, we just try all possible case, then get the smallest one...
            String res = null;

            for (int i = 0; i < n; i++) {
                if (used[i] == '0') {
                    continue;
                }
                used[i] = '0';
                String a = A[i];
                String b = findShortestSuperString(A, used, cnt - 1, map);
                //check if a contains b
                if (a.contains(b)) {
                    if (res == null || res.length() > a.length()) {
                        res = a;
                    }
                } else if (b.contains(a)) { //check if b contains a
                    if (res == null || res.length() > b.length()) {
                        res = b;
                    }
                } else {
                    //check a concatenate with b
                    int index1 = tailHeadOverlap(a, b);
                    String s1 = a + b.substring(index1);
                    if (res == null || res.length() > s1.length()) {
                        res = s1;
                    }
                    //check b concatenate with a
                    int index2 = tailHeadOverlap(b, a);
                    String s2 = b + a.substring(index2);
                    if (res.length() > s2.length()) {
                        res = s2;
                    }
                }
                used[i] = '1';
            }
            map.put(key, res);
        }
        return map.get(key);
    }

    //use the prefix-suffix array of KMP to calculate the number of overlap characters, (prefix of b that is also the suffix of a)
    private int tailHeadOverlap(String a, String b) {
        int m = a.length(), n = b.length();
        if (m > n) {
            a = a.substring(m - n);
        } else if (n > m) {
            b = b.substring(0, m);
        }

        String s = b + "#" + a;
        int k = s.length();
        int[] preSuf = new int[k];

        int j = 0;
        for (int i = 1; i < k; i++) {
            while (j != 0 && s.charAt(i) != s.charAt(j)) {
                j = preSuf[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            preSuf[i] = j;
        }
        return preSuf[k - 1];
    }

    public static void main(String[] args) {
        String[] s = new String[]{"abb", "bbc", "bbb"};
        System.out.println(new ShortestSuperstring().shortestSuperstring(s));
    }


}

/*
 * The used array indicates which word is avaible, used[i] == '1', means the word is available...
 * map record the <used array, shortest string> pairs, means the shortest string we can get using the remaining words..
 */
