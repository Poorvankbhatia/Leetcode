/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have direct prerequisites, for example, to take course 0 you have first to take course 1,
which is expressed as a pair: [1,0]

Given the total number of courses n, a list of direct prerequisite pairs and a list of queries pairs.

You should answer for each queries[i] whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.

Return a list of boolean, the answers to the given queries.

Please note that if course a is a prerequisite of course b and course b is a prerequisite of course c,
then, course a is a prerequisite of course c.

Input: n = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: course 0 is not a prerequisite of course 1 but the opposite is true.
Example 2:

Input: n = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites and each course is independent.

Input: n = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
Example 4:

Input: n = 3, prerequisites = [[1,0],[2,0]], queries = [[0,1],[2,0]]
Output: [false,true]
Example 5:

Input: n = 5, prerequisites = [[0,1],[1,2],[2,3],[3,4]], queries = [[0,4],[4,0],[1,3],[3,0]]
Output: [true,false,true,false]


Constraints:

2 <= n <= 100
0 <= prerequisite.length <= (n * (n - 1) / 2)
0 <= prerequisite[i][0], prerequisite[i][1] < n
prerequisite[i][0] != prerequisite[i][1]
The prerequisites graph has no cycles.
The prerequisites graph has no repeated edges.
1 <= queries.length <= 10^4
queries[i][0] != queries[i][1]

 */
package graph.medium;

import java.util.*;

public class CourseSchedule4 {

    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {

        Set<Integer>[] sets = new Set[n];
        List<Integer>[] lists = new List[n];
        for (int i=0;i<n;i++) {
            lists[i]=new ArrayList<>();
            sets[i]=new HashSet<>();
        }
        int[] inDegree = new int[n];
        for (int[] prerequisite: prerequisites) {
            inDegree[prerequisite[1]]++;
            lists[prerequisite[0]].add(prerequisite[1]);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<inDegree.length;i++) {
            if(inDegree[i]==0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int s = queue.size();
            while (s>0) {
                int pop = queue.poll();
                for (int c : lists[pop]) {
                    inDegree[c]--;
                    if(!visited[c] && inDegree[c]==0) {
                        queue.add(c);
                        visited[c]=true;
                    }
                    sets[c].add(pop);
                    sets[c].addAll(sets[pop]);
                }
                s--;
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] q : queries) {
            result.add(sets[q[1]].contains(q[0]));
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] pre = new int[][]{{2,3},{2,0},{2,1},{3,4},{3,6},{5,1},{5,0},{1,4},{1,0},{4,0},{0,6}};
        int[][] quer = new int[][]{{3,5},{1,6}};
        System.out.println(new CourseSchedule4().checkIfPrerequisite(7,pre,quer));
    }

}

/*

Time: O(P * N), N for topological sort and P for passing all prerequisites.

Another method:
Floyd marshall O(n^3)

public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[n][n];
        for (int[] p : prerequisites)
            connected[p[0]][p[1]] = true; // p[0] -> p[1]
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries)
            ans.add(connected[q[0]][q[1]]);
        return ans;
    }

 */
