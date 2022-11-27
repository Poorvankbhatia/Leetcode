/*
A maze consists of n rooms numbered from 1 to n, and some rooms are connected by corridors.
You are given a 2D integer array corridors where corridors[i] = [room1i, room2i] indicates that there
is a corridor connecting room1i and room2i, allowing a person in the maze to go from room1i to room2i and vice versa.

The designer of the maze wants to know how confusing the maze is. The confusion score of the maze is the number
of different cycles of length 3.

For example, 1 → 2 → 3 → 1 is a cycle of length 3, but 1 → 2 → 3 → 4 and 1 → 2 → 3 → 2 → 1 are not.
Two cycles are considered to be different if one or more of the rooms visited in the first cycle is not in the second cycle.

Return the confusion score of the maze.

Input: n = 5, corridors = [[1,2],[5,2],[4,1],[2,4],[3,1],[3,4]]
Output: 2
Explanation:
One cycle of length 3 is 4 → 1 → 3 → 4, denoted in red.
Note that this is the same cycle as 3 → 4 → 1 → 3 or 1 → 3 → 4 → 1 because the rooms are the same.
Another cycle of length 3 is 1 → 2 → 4 → 1, denoted in blue.
Thus, there are two different cycles of length 3.

Input: n = 4, corridors = [[1,2],[3,4]]
Output: 0
Explanation:
There are no cycles of length 3.


Constraints:

2 <= n <= 1000
1 <= corridors.length <= 5 * 104
corridors[i].length == 2
1 <= room1i, room2i <= n
room1i != room2i
There are no duplicate corridors.

 */
package graph.medium;

import java.util.*;

public class PathLeadToSameRoom {

    public int numberOfPaths(int n, int[][] corridors) {
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] c : corridors) {
            int a = c[0], b = c[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += dfs(graph, i, i, 1);
        }
        return res;
    }

    private int dfs(Set<Integer>[] graph, int cur, int start, int count) {
        // base case
        if (count == 3) {
            if (graph[cur].contains(start)) {
                return 1;
            }
            return 0;
        }

        int ans = 0;
        for (int next : graph[cur]) {
            // to avoid duplicates.
            if (next < cur) continue;
            ans +=dfs(graph, next, start, count + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2},{5,2},{4,1},{2,4},{3,1},{3,4}
        };
        System.out.println(new PathLeadToSameRoom().numberOfPaths(5,a));
    }

}

/*

It's not difficult to figure out to use dfs to solve the problem, the biggest obstacle
here is how to avoid repeated visiting the same visited cycle when starting from different nodes as starting point in this cycle.

The trick here is always traverse in a single monotonic direction, i.e., either from small to large or vice versa.

Then when we count up to 3 nodes in a single path, in order to know the next node could be the starting point,
we need to check if there is a path leading to it from the last node of the 3.
That leads to the second trick - use HashSet instead of List when building the graph from edges at the beginning.

 */