/*

There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node
in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a
directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from
xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most
frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle

Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
Output: 3
Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

Input: colors = "a", edges = [[0,0]]
Output: -1
Explanation: There is a cycle from 0 to 0.


Constraints:

n == colors.length
m == edges.length
1 <= n <= 105
0 <= m <= 105.

 */
package bfsdfs.hard;

import java.util.*;

public class LargestColourValue {

    public int largestPathValue(String colors, int[][] edges) {

        int n = colors.length();
        // Store the largest colour values seen for each of the nodes along all the paths going down from that node.
        int[][] dp = new int[n][26];
        List<Integer>[] lists = new List[n];
        // create the graph.
        for (int[] edge : edges) {
            if(edge[0]==edge[1]) return -1;
            if (lists[edge[0]] == null) lists[edge[0]] = new ArrayList<>();
            lists[edge[0]].add(edge[1]);
        }

        int ans = 0;
        // check if an edge is already visited
        boolean[] visited = new boolean[n];
        // check if an edge is already computed.
        boolean[] computed = new boolean[n];

        // loop over all the nodes.
        for (int i = 0; i < n; i++) {
            // if dfs returns -1, means there is a cycle.
            if(!dfs(i,colors,lists,dp,visited,computed)) {
                return -1;
            }
            // check for max colour.
            for (int j=0;j<26;j++) {
                ans = Math.max(ans,dp[i][j]);
            }
        }

        return ans;

    }

    private boolean dfs(int start, String colors, List<Integer>[] lists, int[][] dp, boolean[] visited,boolean[] computed) {
        // if already visited means there is a cycle return false.
        if (visited[start]) {
            return false;
        }
        // if already computed, no need to compute again
        if(computed[start]) {
            return true;
        }
        // mark visited as true;
        visited[start] = true;
        // loop over children
        if (lists[start] != null) {
            for (int next : lists[start]) {
                // if the child is already visited but not computed means there is a cycle.
                // (1,2) (2,1)
                if(visited[next] && !computed[next]) {
                    return false;
                }
                // else compute the max colour.
                if(dfs(next, colors, lists, dp, visited,computed)) {
                    // figure out the max colour value from all the child branches.
                    for (int i=0;i<26;i++) {
                        dp[start][i] = Math.max(dp[next][i],dp[start][i]);
                    }
                } else {
                    return false;
                }
            }
        }
        // add the current colour.
        int val = (int) colors.charAt(start) - 'a';
        dp[start][val]+= 1;
        computed[start] = true;
        // mark visited as false for the current branch.
        visited[start] = false;
        return true;
    }

    public static void main(String[] args) {
        //int[][] edge = new int[][]{{0,1},{1,2},{2,3},{2,4},{3,5},{4,6},{3,6},{5,6},{6,7},{7,8}};
        //int[][] edge = new int[][]{{1,2},{2,1},{0,2}};
        //System.out.println(new LargestColourValue().largestPathValue("abc",edge));

        int[][] edge = new int[][]{{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}};
        System.out.println(new LargestColourValue().largestPathValue("hhqhuqhqff",edge));
    }

}


/*

Another sol: Topological sort

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        //1. Build the Graph and Indegree array
       ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
        int n=colors.length();
        char[] color=colors.toCharArray();

        for(int i=0;i<n;i++) graph.add(i,new ArrayList<>());
        int[] indegree=new int[n];
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            indegree[v]++;
            graph.get(u).add(v);
        }

		//2. color count map. map[i][j] is the maximum count of j-th color from the ancester nodes to node i
        int[][] map=new int[n][26];

		//3.Khans Algorithm ( Iterative Topological Sort)
        Queue<Integer> que=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                que.add(i);
                map[i][color[i]-'a']=1;
            }
        }

        int res=0;
        int seen=0;

        while(que.size()>0){
            int node=que.remove();
            seen++;

            int max=getMax(map[node]);
            res=Math.max(res,max);

            for(int nbr:graph.get(node)){
                // update the map of next node
                for(int i=0;i<26;i++){
                    map[nbr][i]=Math.max(map[nbr][i],map[node][i] + (color[nbr]-'a'==i?1:0));
                }
                indegree[nbr]--;

                if(indegree[nbr]==0){
                    que.add(nbr);
                }
            }
        }
        //if seen!=n means cycle is there
        return seen==n?res:-1;
    }

    private int getMax(int[] num){
        int max=num[0];
        for(int n:num){
            max=Math.max(n,max);
        }
        return max;
    }

}

 */