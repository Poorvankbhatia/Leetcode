/*
You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.

You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

You need to assign each city with an integer value from 1 to n, where each value can only be used once.
The importance of a road is then defined as the sum of the values of the two cities it connects.

Return the maximum total importance of all roads possible after assigning the values optimally.


Input: n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
Output: 43
Explanation: The figure above shows the country and the assigned values of [2,4,5,3,1].
- The road (0,1) has an importance of 2 + 4 = 6.
- The road (1,2) has an importance of 4 + 5 = 9.
- The road (2,3) has an importance of 5 + 3 = 8.
- The road (0,2) has an importance of 2 + 5 = 7.
- The road (1,3) has an importance of 4 + 3 = 7.
- The road (2,4) has an importance of 5 + 1 = 6.
The total importance of all roads is 6 + 9 + 8 + 7 + 7 + 6 = 43.
It can be shown that we cannot obtain a greater total importance than 43.

Input: n = 5, roads = [[0,3],[2,4],[1,3]]
Output: 20
Explanation: The figure above shows the country and the assigned values of [4,3,2,5,1].
- The road (0,3) has an importance of 4 + 5 = 9.
- The road (2,4) has an importance of 2 + 1 = 3.
- The road (1,3) has an importance of 3 + 5 = 8.
The total importance of all roads is 9 + 3 + 8 = 20.
It can be shown that we cannot obtain a greater total importance than 20.

 */
package graph.medium;

import java.util.*;

public class MaximumImportance {

    public long maximumImportance(int n, int[][] roads) {
        int[] count = new int[n];
        int max = Integer.MIN_VALUE;
        // compute indegree
        for (int[] road : roads) {
            count[road[0]]++;
            count[road[1]]++;
            max = Math.max(max, count[road[0]]);
            max = Math.max(max, count[road[1]]);
        }
        // compute indegree to node connection i.e indegree of 4 belongs to node 2
        List<Integer>[] invertedCount = new List[max+1];
        for (int i=0;i<count.length;i++) {
            if(invertedCount[count[i]]==null) {
                invertedCount[count[i]] = new ArrayList<>();
            }
            invertedCount[count[i]].add(i);
        }
        // Assignment
        Map<Integer,Integer> assignments = new HashMap<>();
        int value = n;
        for (int i=max;i>0;i--) {
            if (invertedCount[i] != null) {
                for (int v : invertedCount[i]) {
                    assignments.put(v,value--);
                }
            }
        }
        long ans = 0;
        for (int[] road : roads) {
           ans+= assignments.get(road[0])+assignments.get(road[1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] roads = new int[][] {
                {0,1},{1,2},{2,3},
                {0,2},{1,3},{2,4}
        };
        System.out.println(new MaximumImportance().maximumImportance(5,roads));
    }

}

/*

Nodes with greatest indegree need to have the highest value n.. and the lowest indegree should have value 1.

Sort solution:

public long maximumImportance(int n, int[][] roads) {
        long ans = 0, x = 1;
		long degree[] = new long[n];
        for(int road[] : roads){
            degree[road[0]]++;
			degree[road[1]]++;
        }
        Arrays.sort(degree);
        for(long i : degree) ans +=  i * (x++) ;
        return ans;
    }

 */