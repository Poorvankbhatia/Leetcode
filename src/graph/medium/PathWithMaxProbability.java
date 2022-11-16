/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b]
is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and
return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct
answer by at most 1e-5.

Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.



Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000

 */
package graph.medium;

import java.util.*;

public class PathWithMaxProbability {

    private static class Pair {
        int vertex;
        Double probability;

        public Pair(int vertex, double probability) {
            this.vertex = vertex;
            this.probability = probability;
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

        List<Pair>[] pairs = new List[n];
        for (int i=0;i<edges.length;i++) {
            if(pairs[edges[i][0]] == null) pairs[edges[i][0]] = new ArrayList<>();
            if(pairs[edges[i][1]] == null) pairs[edges[i][1]] = new ArrayList<>();
            pairs[edges[i][0]].add(new Pair(edges[i][1], succProb[i]));
            pairs[edges[i][1]].add(new Pair(edges[i][0], succProb[i]));
        }
        double[] maxProb = new double[n];
        Arrays.fill(maxProb,Double.MIN_VALUE);
        maxProb[start]=1;
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a, b)->(b.probability.compareTo(a.probability)));
        priorityQueue.add(new Pair(start,1));
        while (!priorityQueue.isEmpty()) {
            Pair poll = priorityQueue.poll();
            if(pairs[poll.vertex]!=null) {
                for (Pair pair : pairs[poll.vertex]) {
                    if(maxProb[pair.vertex]<(poll.probability*pair.probability)) {
                        maxProb[pair.vertex] = poll.probability*pair.probability;
                        priorityQueue.add(new Pair(pair.vertex,maxProb[pair.vertex]));
                    }
                }
            }
        }
        return maxProb[end]==Double.MIN_VALUE?0:maxProb[end];
    }

    public static void main(String[] args) {
        int[][] a = new int[][] {
                {0,1}
        };
        System.out.println(new PathWithMaxProbability().maxProbability(3,a,new double[]{0.5},0,2));
    }

}


/*

Simple Dijikstra's algo

Time complexity of the above code is O(E+ElogN)_ which turns out to be O(ElogN) where N is the number of Nodes and E
is the number of edges.

Explanation for the same:
Maximum Nodes in the priority queue can Be E(Edges), And we know that each operation in the priority queue takes
O(logx) time where x is the size of the priority queue, Here it turns out to be O(logE),
Which in fact can be written as O(logN). Reason begin worst value of E can be N*(N-1)/2 ~ N2.
So logE can be written as logN2 = 2*logN = logN. Hence the time complexity required due to priority queue is O(N)

Also in the question we are creating adjacency list, For which we are looping E times. Hence the time complexity due to the same is O(E).

Total time complexity O(E+ElogN) = O(max(E, ElogN) = (E*logN).
 */