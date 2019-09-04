/*

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair
with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike)
pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that,
we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.


Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation:
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].


Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation:
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2,
thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].


Note:

0 <= workers[i][j], bikes[i][j] < 1000
All worker and bike locations are distinct.
1 <= workers.length <= bikes.length <= 1000


 */
package arrays.medium;

import java.util.*;

public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {

        TreeMap<Integer,List<int[]>> disMap = new TreeMap<>();
        Set<Integer> bikeAssigned = new HashSet<>();
        Set<Integer> workerGot = new HashSet<>();
        int[] result = new int[workers.length];

        for(int i=0;i<workers.length;i++) {
            for(int j=0; j<bikes.length;j++) {
                int current = findManhattanDistance(workers[i],bikes[j]);
                int[] arr = new int[]{i,j};
                if(!disMap.containsKey(current)) {
                    disMap.put(current, new ArrayList<>());
                }
                disMap.get(current).add(arr);
            }
        }
        for(Map.Entry<Integer,List<int[]>> entry : disMap.entrySet()) {
            List<int[]> values = entry.getValue();
            for(int[] v : values) {
                if(!bikeAssigned.contains(v[1]) && !workerGot.contains(v[0])) {
                    result[v[0]]=v[1];
                    bikeAssigned.add(v[1]);
                    workerGot.add(v[0]);
                }
            }
        }

        return result;

    }


    private int findManhattanDistance(int[] a,int[] b) {
        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
    }

    public static void main(String[] args) {
        int[][] workers = new int[][]{
                {0,0},
                {2,1}
        };
        int[][] bikes = new int[][]{
                {1,2},
                {3,3}
        };
        System.out.println(Arrays.toString(new CampusBikes().assignBikes(workers, bikes)));
    }

}

/*

Complexity : O(M*N*logn)

O(M*N) complexity:

0 <= workers[i][j], bikes[i][j] < 1000. So that means Manhattan distance
between any worker and bike is between 0 and 2000. So we could use the following counting sort solution with time complexity of O(M*N)

public int[] assignBikes(int[][] workers, int[][] bikes) {
        // Notice that the Manhattan distance is between 0 and 2000,
        // which means we can sort easily without even using priority queue
        int w = workers.length, b = bikes.length;
        int[] wo = new int[w], bi = new int[b];
        List<int[]>[] dists = new List[2001];
        Arrays.fill(wo, -1);
        Arrays.fill(bi, -1);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < b; j++) {
                int[] worker = workers[i];
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                if (dists[dist] == null) {
                    dists[dist] = new ArrayList<int[]>();
                }
                dists[dist].add(new int[]{i, j});
            }
        }
        int assigned = 0;
        for (int i = 0; i <= 2000 && assigned < w; i++) {
            if (dists[i] == null) continue;
            for (int[] pair : dists[i]) {
                if (wo[pair[0]] == -1 && bi[pair[1]] == -1) {
                    wo[pair[0]] = pair[1];
                    bi[pair[1]] = pair[0];
                    assigned++;
                }
            }
        }
        return wo;
    }




 */
