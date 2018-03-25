/*

In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.
 More specifically, there exists a natural number K so that for any choice of where to walk, we must have
 stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is
given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 25/03/18.
 */
public class EventualSafeStates {

    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<Integer> result = new ArrayList<>();

        if (graph == null || graph.length == 0) {
            return result;
        }

        int n = graph.length;
        boolean[] loopNodes = new boolean[n];
        boolean[] safeNodes = new boolean[n];

        for (int i=0;i<graph.length;i++) {
            if(!isLoopNode(graph,loopNodes,safeNodes,i)) {
                result.add(i);
            }
        }

        return result;

    }

    private boolean isLoopNode(int[][] graph,boolean[] loopNodes,boolean[] safeNodes,int start) {

        if(loopNodes[start]) {
            return true;
        } else if(safeNodes[start]) {
            return false;
        } else {
            loopNodes[start]=true;
            for (int ne : graph[start]) {
                if(isLoopNode(graph,loopNodes,safeNodes,ne)) {
                    return true;
                }
            }
            loopNodes[start]=false;
            safeNodes[start]=true;
            return false;
        }
    }

}

/*

BETTER SOL:

value of color represents three states:
0:have not been visited
1:safe
2:unsafe
For DFS,we need to do some optimization.When we travel a path,we mark the node with 2 which represents having been visited,and when we
encounter a node which results in a cycle,we return false,all node in the path stays 2 and it represents unsafe.And in the following traveling,
whenever we encounter a node which points to a node marked with 2,we know it will results in a cycle,so we can stop traveling.
On the contrary,when a node is safe,we can mark it with 1 and whenever we encounter a safe node,we know it will not results in a cycle.

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if(graph == null || graph.length == 0)  return res;

        int nodeCount = graph.length;
        int[] color = new int[nodeCount];

        for(int i = 0;i < nodeCount;i++){
            if(dfs(graph, i, color))    res.add(i);
        }

        return res;
    }
    public boolean dfs(int[][] graph, int start, int[] color){
        if(color[start] != 0)   return color[start] == 1;

        color[start] = 2;
        for(int newNode : graph[start]){
            if(!dfs(graph, newNode, color))    return false;
        }
        color[start] = 1;

        return true;
    }
}

 */
