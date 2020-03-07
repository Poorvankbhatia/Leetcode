/*

Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group.

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.



Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false


Note:

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].

 */
package bfsdfs.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 14/08/18.
 */
public class PossibleBipartition {

    class Solution {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            int[] color = new int[N + 1]; // because numbered from 1 to N
            List<List<Integer>> adj = new ArrayList<>(N + 1);
            for(int i = 0; i <= N; i++) {
                adj.add(new ArrayList<>());
            }
            for(int[] d : dislikes) {
                adj.get(d[0]).add(d[1]);
                adj.get(d[1]).add(d[0]);
            }

            for(int i = 1; i <= N; i++) {
                if(color[i] == 0) {
                    color[i] = 1;
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);
                    while(!q.isEmpty()) {
                        int cur = q.poll();
                        for(int nb : adj.get(cur)) {
                            if(color[nb] == 0) {
                                color[nb] = color[cur] == 1 ? 2 : 1;
                                q.add(nb);
                            } else {
                                if(color[nb] == color[cur]) return false;
                            }
                        }
                    }
                }
            }
            return true;
        }

    }

}
