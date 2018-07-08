/*

In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.

For convenience, we'll call the person with label x, simply "person x".

We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of
valid observations.

Also, we'll say quiet[x] = q if person x has quietness q.

Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]),
among all people who definitely have equal to or more money than person x.



Example 1:

Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
Output: [5,5,2,5,4,5,6,7]
Explanation:
answer[0] = 5.
Person 5 has more money than 3, which has more money than 1, which has more money than 0.
The only person who is quieter (has lower quiet[x]) is person 7, but
it isn't clear if they have more money than person 0.

answer[7] = 7.
There isn't anyone who definitely has more money than person 7, so the person who definitely has
equal to or more money than person 7 is just person 7.

The other answers can be filled out with similar reasoning.
Note:

1 <= quiet.length = N <= 500
0 <= quiet[i] < N, all quiet[i] are different.
0 <= richer.length <= N * (N-1) / 2
0 <= richer[i][j] < N
richer[i][0] != richer[i][1]
richer[i]'s are all different.
The observations in richer are all logically consistent.

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 10/06/18.
 */
public class LoudAndRich {

    private int [] sol;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;
        Map<Integer, List<Integer>> richerMap = new HashMap<>();
        for(int [] rich : richer){
            richerMap.putIfAbsent(rich[1], new ArrayList<>());
            richerMap.get(rich[1]).add(rich[0]);
        }

        sol = new int[N];
        Arrays.fill(sol, -1);
        for(int i = 0; i < sol.length; ++i) {
            if(sol[i] == -1) {
                sol[i] = dfs(richerMap, i, quiet);
            }
        }


        return sol;
    }

    private int dfs(Map<Integer, List<Integer>> richerMap, int vertex, int [] quiet){
        if(sol[vertex] != -1) {
            return sol[vertex];
        }
        int ans = -1;
        for(int x : richerMap.getOrDefault(vertex, new ArrayList<>())){
            int next = dfs(richerMap, x, quiet);
            if(ans == -1 || quiet[next] < quiet[ans]) {
                ans = next;
            }
        }
        if(ans != -1 && (quiet[ans] < quiet[vertex])) {
            sol[vertex] = ans;
        }
        else {
            sol[vertex] = vertex;
        }
        return sol[vertex];
    }

    public static void main(String[] args) {
        int[][] arr= new int[][] {
                {1,0},
                {2,1},
                {3,1},
                {3,7},
                {4,3},
                {5,3},
                {6,3}
        };
        int[] quiet = new int[]{3,2,5,4,6,1,7,0};
        System.out.println(Arrays.toString(new LoudAndRich().loudAndRich(arr, quiet)));
    }

}
