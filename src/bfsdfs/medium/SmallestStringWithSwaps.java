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
