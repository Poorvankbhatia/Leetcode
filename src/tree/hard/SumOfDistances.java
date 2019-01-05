/*

An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation:
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000

 */
package tree.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by poorvank.b on 05/01/19.
 */
public class SumOfDistances {

    Map<Integer, HashSet<Integer>> map;
    private int[] ans;
    private int[] count;
    private int N;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        map = new HashMap<>();
        this.N = N;
        count = new int[N];
        Arrays.fill(count, 1);
        ans = new int[N];
        for (int i = 0; i < N; i++) {
            map.put(i, new HashSet<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        dfs(0, -1);
        dfs2(0, -1);
        return ans;


    }

    private void dfs(int node, int parent) {
        for (int child : map.get(node))
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
    }

    private void dfs2(int node, int parent) {
        for (int child : map.get(node))
            if (child != parent) {
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
    }


}

/*

Let ans be the returned answer, so that in particular ans[x] be the answer for node x.

Naively, finding each ans[x] would take O(N)
time (where N is the number of nodes in the graph), which is too slow.
This is the motivation to find out how ans[x] and ans[y] are related, so that we cut down on repeated work.

Let's investigate the answers of neighboring nodes x and y.
In particular, say xy is an edge of the graph, that if cut would form two trees X (containing x) and Y (containing y).

Look at image:

Then, as illustrated in the diagram, 
the answer for x in the entire tree, is the answer of x on x "x@X", plus the answer of y on Y "y@Y",
plus the number of nodes in YY "#(Y)". The last part "#(Y)" is specifically because for any node z in Y, dist(x, z) = dist(y, z) + 1.

By similar reasoning, the answer for yy in the entire tree is ans[y] = x@X + y@Y + #(X).
Hence, for neighboring nodes x and yy, ans[x] - ans[y] = #(Y) - #(X).

 */