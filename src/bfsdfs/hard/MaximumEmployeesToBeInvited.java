/*

A company is organizing a meeting and has a list of n employees, waiting to be invited.
They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will
attend the meeting only if they can sit next to their favorite person at the table.
The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the
ith employee, return the maximum number of employees that can be invited to the meeting.

Input: favorite = [2,2,1,2]
Output: 3
Explanation:
The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
The maximum number of employees that can be invited to the meeting is 3.
Example 2:

Input: favorite = [1,2,0]
Output: 3
Explanation:
Each employee is the favorite person of at least one other employee, and the only way the company can invite
them is if they invite every employee.
The seating arrangement will be the same as that in the figure given in example 1:
- Employee 0 will sit between employees 2 and 1.
- Employee 1 will sit between employees 0 and 2.
- Employee 2 will sit between employees 1 and 0.
The maximum number of employees that can be invited to the meeting is 3.

Input: favorite = [3,0,1,4,1]
Output: 4
Explanation:
The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
So the company leaves them out of the meeting.
The maximum number of employees that can be invited to the meeting is 4.


Constraints:

n == favorite.length
2 <= n <= 105
0 <= favorite[i] <= n - 1
favorite[i] != i

 */
package bfsdfs.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumEmployeesToBeInvited {

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0; i< n; i++){
            int fav = favorite[i];
            adj.get(fav).add(i);
        }
        int[] visited = new int[n];
        Arrays.fill(visited, -1);
        //case 1, where two nodes likes each other and there could be max path going across these two node.
        int res1= 0;
        for(int i =0; i< n;i++){
            if(visited[i] == 1)
                continue;
            if(i == favorite[favorite[i]]){
                //block path from going i to fav[i] and vice-versa
                visited[favorite[i]] = 1;
                visited[i] = 1;

                //get the maximum path from i to its likers
                int a = dfs(adj, visited, i);
                //get the maximum path from favorite[i] to its likers
                int b = dfs(adj, visited, favorite[i]);

                res1 += a + b + 2;
            }
        }
        //case 2, maximum result from cycle
        int res2 = 0;
        Arrays.fill(visited, -1);
        for(int i = 0; i<n; i++){
            if(visited[i] ==1) continue;
            res2 = Math.max(res2,  dfs2(adj, visited, i, -1)[1]);
        }
        return Math.max(res1, res2);
    }
    //[{found=1/not found=0}, {depth}]
    private int[] dfs2(List<List<Integer>> adj, int[] visited, int u, int start){
        if(start == u){
            return new int[]{1, 0};
        }
        if(visited[u] == 1) return new int[]{0,0};
        visited[u] = 1;

        int[] res = new int[2];
        for(int v : adj.get(u)){
            int[] ret = dfs2(adj, visited, v, start==-1? u : start);
            if(ret[0] == 1){
                int depth = 1 + ret[1];
                res[1] = Math.max(depth, res[1]);
            }

            if(res[1] > 0){
                res[0] = 1;
            }
        }

        if(res[1] == 0) return new int[]{0, 0};
        return res;
    }

    private int dfs(List<List<Integer>> adj, int[] visited, int u){
        visited[u] =1;
        int d  =0;
        for(int v : adj.get(u)){
            if(visited[v] != 1){
                int res = 1 + dfs(adj, visited, v);
                d = Math.max(d, res);
            }
        }
        return d;
    }

}
