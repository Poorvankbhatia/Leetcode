/*

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.


Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: 6
Explanation:
We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3, so the output is 6.


Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: 4
Explanation:
We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2, bike 2 to worker 2 or worker 1.
 Both assignments lead to sum of the Manhattan distances as 4.

 */
package dyanamicprogramming.medium;

import java.util.*;

public class CampusBikes2 {

    private int max;
    private Map<String, Integer> map;
    public int assignBikes(int[][] workers, int[][] bikes) {
        max = Integer.MAX_VALUE;
        map = new HashMap<>();
        return dfs(workers, bikes, new boolean[bikes.length], 0);
    }

    private int dfs(int[][] workers, int[][] bikes, boolean[] visited, int count) {
        if (count == workers.length) {
            return 0;
        }
        String key = getKey(visited);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int val = dist(workers[count], bikes[i]) + dfs(workers, bikes, visited, count+1);
                if (val < min) {
                    min = val;
                }
                visited[i] = false;
            }
        }

        map.put(key, min);
        return min;
    }

    private int dist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    private String getKey(boolean[] visited) {
        StringBuilder stringBuilder = new StringBuilder();
        for (boolean b : visited) {
            if (b) {
                stringBuilder.append('t');
            } else {
                stringBuilder.append('f');
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        int[][] workers = new int[][]{
                {815,60},
                {638, 626},
                {6,44},
                {103,90},
                {591,880}
        };
        int[][] bikes = new int[][]{
                {709,161},
                {341,339},
                {755,955},
                {172,27},
                {433,489}
        };
        System.out.println((new CampusBikes2().assignBikes(workers, bikes)));
    }


}

/*

DFS Solution:
 Map<String,Integer> map = new HashMap<>();
    private int min = Integer.MAX_VALUE;

    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(workers,bikes,new boolean[bikes.length],0,0);
        return min;
    }


    private void dfs(int[][] workers, int[][] bikes, boolean[] visited,int count,int sum) {

        if(count>=workers.length) {
            if(sum<min) {
                min = sum;
            }
            return;
        }

        for (int i=0;i<bikes.length;i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(workers,bikes,visited,count+1,(sum + findManhattanDistance(workers[count], bikes[i])));
                visited[i] = false;
            }
        }

    }

    private int findManhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

Lets say x is a worker, y is bike
for 4 workers and 4 bikes, we expect the algorithm to go through paths like

x0y0, x1y1, x2y2, x3y3
x0y0, x1y1, x2y3, x3y2
x0y0, x1y2, x2y1, x3y3
x0y0, x1y2, x2y3, x3y1
x0y0, x1y3, x2y1, x3y2
x0y0, x1y3, x2y2, x3y2

x0y1, x1y0, x2y2, x3y3
x0y1, x1y0, x2y3, x3y2
x0y1, x1y2, x2y0, x3y1
x0y1, x1y2, x2y1, x3y0
...

Notice the bolded paths here repeats themself. We can prevent calculating this path again with a memoization technique.
We can reword the pattern above as:

x0yn, x1yn, x2y2, x3y3
x0yn, x1yn, x2y3, x3y2

Since the nature of this problem asks for the shortest distance, so whenever we encounter the above 2 possible paths,
we will always return it as Math.min(f(x2y2, x3y3), f(x2y3 ,x3y2))

Now the quesiton becomes how do we store Math.min(f(x2y2, x3y3), f(x2y3 ,x3y2))
Since the dfs function iterates through each worker from 0-nth, we can map them to an boolean array of boolean[workers.length]
Meaning if we ever see a boolean array of {[true], [true], [false], [false]} it will always refer to {x0yn, x1yn, x2y2, x3y3} or {x0yn, x1yn, x2y3, x3y2}.



    Integer key can be constructed as(BIT MASKING):
     private int createKey(boolean[] visited) {
        int key = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                key |= 1 << i;
            }
        }
        return key;
    }

 */