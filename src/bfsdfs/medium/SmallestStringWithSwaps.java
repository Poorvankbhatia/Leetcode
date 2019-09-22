/*
You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.



Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
Example 2:

Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"
Example 3:

Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination:
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"



Constraints:

1 <= s.length <= 10^5
0 <= pairs.length <= 10^5
0 <= pairs[i][0], pairs[i][1] < s.length
s only contains lower case English letters.
 */
package bfsdfs.medium;

import bfsdfs.UnionFind;

import java.util.*;

public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> swaps) {
        int N = s.length();

        UnionFind uf = new UnionFind(N);
        for (List<Integer> swap : swaps) {
            uf.union(swap.get(0), swap.get(1));
        }


        Map<Integer, List<Character>> graphs = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int head = uf.find(i);
            List<Character> characters = graphs.computeIfAbsent(head, (dummy) -> new ArrayList<>());
            characters.add(s.charAt(i));
        }

        for (List<Character> characters : graphs.values()) {
            Collections.sort(characters);
        }

        StringBuilder sb = new StringBuilder(N);
        for (int i = 0; i < N; i++) {
            List<Character> characters = graphs.get(uf.find(i));
            char currentMin = characters.remove(0);
            sb.append(currentMin);
        }
        return sb.toString();


    }

}

/*

If we union the swap pairs, we end up with a set of disjoint indexes representing separate graphs.
For every character index, find the graph the character belongs to & find the smallest permutation for every graph.

-> Extract separate graphs and map the string’s characters to the appropriate vertices.
-> Sort the characters in descending order within each graph.
-> Populate the output by taking the characters from the graph.
 */
