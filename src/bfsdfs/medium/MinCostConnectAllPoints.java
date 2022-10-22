/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.


Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:

Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18
 */
package bfsdfs.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinCostConnectAllPoints {

    Map<int[],int[]> parentMap = new HashMap<>();
    Map<int[],Integer> sizeMap = new HashMap<>();


    private static class Connection {
        private int val;
        private int[] start;
        private int[] end;

        public Connection(int val, int[] start, int[] end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Connection> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                parentMap.put(points[i],points[i]);
                sizeMap.put(points[i],1);
                parentMap.put(points[j],points[j]);
                sizeMap.put(points[j],1);
                pq.offer(new Connection(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]),
                        points[i], points[j]));
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Connection poll = pq.poll();
            if(!isConnected(poll.start,poll.end)) {
                union(poll.start, poll.end);
                ans+=poll.val;
            }
        }
        return ans;

    }

    private void union(int[] a,int[] b) {
        int[] pA = parent(a);
        int[] pB = parent(b);

        if(pA!=pB) {
            if(sizeMap.get(pA)>=sizeMap.get(pB)) {
                parentMap.put(pB,pA);
                sizeMap.put(pA,sizeMap.get(pB)+1);
            } else {
                parentMap.put(pA,pB);
                sizeMap.put(pB,sizeMap.get(pA)+1);
            }
        }
    }

    private boolean isConnected(int[] a,int[] b) {
        int[] pA = parent(a);
        int[] pB = parent(b);
        return pA[0] == pB[0] && pA[1]==pB[1];
    }

    private int[] parent(int[] p) {
        while(p!=parentMap.get(p)) {
            parentMap.put(p,parentMap.get(parentMap.get(p)));
            p = parentMap.get(p);
        }
        return p;
    }




    public static void main(String[] args) {
        int[][] a = new int[][]{
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };
        System.out.println(new MinCostConnectAllPoints().minCostConnectPoints(a));
    }

}

/*

Prims:

//This edge will tell from which node to which node i am connected and what's its cost
    class Edge {
        int point1;
        int point2;
        int cost;

        public Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }


    public int minCostConnectPoints(int[][] points) {

        if (points == null || points.length == 0)
            return 0;

        int n = points.length;
        boolean[] visited = new boolean[n];

        //we only want n-1 edges if there are n points as we dont want a cycle
        int requiredEdges = n - 1;
        int minCost = 0;

        //we always want min cost to choose 1st, so lets use minheap based on cost
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        int[] coordinate1 = points[0];

        //find all possible paths from 0 to n and add its cost to minHeap
        //here indexes are considered as nodes
        for (int i = 1; i < points.length; i++) {
            int[] coordinate2 = points[i];
            int cost = Math.abs(coordinate2[0] - coordinate1[0])
                    + Math.abs(coordinate2[1] - coordinate1[1]);
            Edge e = new Edge(0, i, cost);
            priorityQueue.add(e);
        }

        //lets mark 0 as visited bcz we are gonna start from 0 -> finding minCost to another node
        visited[0] = true;

        //we will check till requiredEdges become 0, as we need know initial value was set to n-1
        while (!priorityQueue.isEmpty() && requiredEdges > 0) {
            Edge e = priorityQueue.poll();
            int point2 = e.point2;
            int cost = e.cost;

            //we took the shortest point and see if its already visited, if not lets explore
            if (!visited[point2]) {

                //add the cost and mark as visited
                minCost += cost;
                visited[point2] = true;

                //now lets explore from point 2 to all other possible nodes
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        int distance = Math.abs(points[point2][0] - points[i][0])
                                + Math.abs(points[point2][1] - points[i][1]);

                        priorityQueue.add(new Edge(point2, i, distance));
                    }
                }

                //after this if loop is executed successfully we know we considered a edges, lets decrement it
                requiredEdges--;
            }
        }

        return minCost;
    }

 */
