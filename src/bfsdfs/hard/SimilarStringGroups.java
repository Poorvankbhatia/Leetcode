/*

Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar
to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the
same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at
least one other word in the group.

We are given a list A of unique strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2
Note:

A.length <= 2000
A[i].length <= 1000
A.length * A[i].length <= 20000
All words in A consist of lowercase letters only.
All words in A have the same length and are anagrams of each other.

 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

/**
 * Created by poorvank.b on 20/05/18.
 */
public class SimilarStringGroups {

    public int numSimilarGroups(String[] A) {
        int N = A.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; ++i) {
            for (int j = i+1; j < N; ++j) {
                if (similar(A[i], A[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    public boolean similar(String a, String b) {
        int res = 0, len = a.length();
        for (int i = 0; i < len; i++)
            if (a.charAt(i) != b.charAt(i) && ++res > 2) {
                return false;
            }
        return true;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"tars","rats","arts","star"};
        System.out.println(new SimilarStringGroups().numSimilarGroups(arr));
    }

}

/*

Another method:

public int numSimilarGroups(String[] A) {
        int N = A.length;
        int W = A[0].length();
        UnionFind uf = new UnionFind(N);

        if (N < W*W) { // If few words, then check for pairwise similarity: O(N^2 W)
            for (int i = 0; i < N; ++i) {
                for (int j = i+1; j < N; ++j) {
                    if (similar(A[i], A[j])) {
                        uf.union(i, j);
                    }

                }
            }


        } else { // If short words, check all neighbors: O(N W^3)
            Map<String, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < N; ++i) {
                char[] L = A[i].toCharArray();
                for (int j = 0; j < L.length; j++)
                    for (int k = j + 1; k < L.length; k++) {
                        swap(L, j,k);
                        StringBuilder sb = new StringBuilder();
                        for (char c: L) {
                            sb.append(c);
                        }
                        map.computeIfAbsent(sb.toString(), x-> new ArrayList<>()).add(i);
                        swap(L, j,k);
                    }
            }

            for (String word: A) { // plus O(W^4), but W*W <= N.
                int i1 = map.get(word).get(0);
                for (int i2: map.get(word))
                    uf.union(i1, i2);
            }
        }

        return uf.count;
    }

    public boolean similar(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); ++i)
            if (word1.charAt(i) != word2.charAt(i))
                diff++;
        return diff <= 2;
    }

    public void swap(char[] A, int i, int j) {
        char tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
 }

Let W = A[0].length. It is clear that we can determine in O(W) time, whether two words from A are similar.

One attempt is a standard kind of brute force: for each pair of words, let's draw an edge between these words if they are similar.
 We can do this in O(N^2 W) time. After, finding the connected components can be done in O(N^2) time naively
 (each node may have up to N-1 edges), (or O(N) with a union-find structure.) The total complexity is O(N^2 W)

Another attempt is to enumerate all neighbors of a word. A word has up to WC2 neighbors, and if that neighbor is itself a given word,
we know that word and neighbor are connected by an edge. In this way, we can build the graph in O(N W^3) time, and again take O(N^2)
 or O(N) time to analyze the number of connected components.

One insight is that between these two approaches, we can choose which approach works better. If we have very few words, we want to use the first approach;
if we have very short words, we want to use the second approach. We'll piecewise add these two approaches (with complexity O(N^2 W) and O(N W^3)
 to create an approach with O(NWmin(N,W^2))complexity.

Algorithm

We will build some underlying graph with N nodes, where nodes i and j are connected if and only if A[i] is similar to A[j],
then look at the number of connected components.

There are a few challenges involved in this problem, but each challenge is relatively straightforward.

Use a helper function similar(word1, word2) that is true if and only if two given words are similar.

Enumerate all neighbors of a word, and discover when it is equal to a given word.

Use either a union-find structure or a depth-first search, to calculate the number of connected components of the underlying graph.
We've showcased a union-find structure in this solution, with notes of a depth-first search in the comments.

 */
