/*

Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A
exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

 */
package bfsdfs.hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by poorvank.b on 21/06/18.
 */
public class KSimilarStrings {

    public int kSimilarity(String A, String B) {
        if (A.equals(B)) {
            return 0;
        }
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(A);
        vis.add(A);
        int res = 0;
        while (!q.isEmpty()) {
            for (int size = q.size(); size > 0; size--) {
                String s = q.poll();
                int i = 0;
                while (s.charAt(i) == B.charAt(i)) {
                    i++;
                }
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == B.charAt(j)) {
                        String temp = swap(s, i, j);
                        if (temp.equals(B)) {
                            return res+1;
                        }
                        if (vis.add(temp)) {
                            q.add(temp);
                        }
                    }
                }
            }
            res++;
        }
        return res;
    }

    public String swap(String s, int i, int j) {
        char[] ca = s.toCharArray();
        char temp = ca[i];
        ca[i] = ca[j];
        ca[j] = temp;
        return new String(ca);
    }

    public static void main(String[] args) {
        System.out.println(new KSimilarStrings().kSimilarity("abc","bca"));
    }

}
